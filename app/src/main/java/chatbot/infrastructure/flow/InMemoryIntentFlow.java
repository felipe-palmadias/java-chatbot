package chatbot.infrastructure.flow;

import chatbot.domain.Intent;
import chatbot.domain.IntentFlow;
import chatbot.domain.IntentHandler;
import chatbot.domain.Message;
import chatbot.infrastructure.Serializer;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

public class InMemoryIntentFlow implements IntentFlow {

    private static final int WELCOME_STEP_INDEX = 0;
    private final Flow flow;
    private final IntentHandler intentHandler;
    private int currentStep;

    public InMemoryIntentFlow(File flowFile, Serializer serializer, IntentHandler intentHandler) {
        this.flow = readFlow(flowFile, serializer);
        this.intentHandler = intentHandler;
    }

    private Flow readFlow(File flowFile, Serializer serializer) {
        try {
            return serializer.deserialize(Files.readString(flowFile.toPath()), Flow.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Intent welcome() {
        try {
            return flow.intents.stream()
                    .filter(step -> step.name.equals("welcome"))
                    .findFirst()
                    .map(this::intentOf)
                    .orElseThrow();
        } finally {
            currentStep = WELCOME_STEP_INDEX;
        }
    }

    @Override
    public Optional<Intent> nextBy(Message message) {
        Step step = flow.intents.get(currentStep);
        return intentOf(message, step);
    }

    private Optional<Intent> intentOf(Message message, Step step) {
        for (int i = 0; i < step.options().size(); i++) {
            Option option = step.options.get(i);
            if (message.text().matches(option.condition())) {
                currentStep = i;
                return Optional.of(intentOf(option));
            }
        }
        return Optional.empty();
    }

    private Intent intentOf(Option option) {
        return flow.intents().stream()
                .filter(step -> step.name().equals(option.next()))
                .map(this::intentOf)
                .findFirst()
                .orElseThrow();
    }

    private Intent intentOf(Step step) {
        return new Intent(step.name, step.response, intentHandler);
    }

    private record Flow(List<Step> intents) {}

    private record Step(String name, String response, List<Option> options) {}

    private record Option(String condition, String next) {}
}

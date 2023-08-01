package ut.chatbot.infrastructure.flow;

import chatbot.domain.Intent;
import chatbot.domain.IntentHandler;
import chatbot.infrastructure.DefaultSerializer;
import chatbot.infrastructure.flow.InMemoryIntentFlow;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import static chatbot.domain.MessageFixture.aSimpleMessageWith;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class InMemoryIntentFlowTest {

    @Test
    public void returnsWelcomeIntent() {
        InMemoryIntentFlow intentFlow =
                new InMemoryIntentFlow(intentFlowFile(), new DefaultSerializer(), mock(IntentHandler.class));

        Intent intent = intentFlow.welcome();

        assertThat(intent.text(), is("Welcome to chatbot. Type 1 or 2."));
    }

    @Test
    public void returnsNextStep() {
        InMemoryIntentFlow intentFlow =
                new InMemoryIntentFlow(intentFlowFile(), new DefaultSerializer(), mock(IntentHandler.class));
        intentFlow.welcome();

        Intent intent = intentFlow.nextBy(aSimpleMessageWith("2")).orElseThrow();

        assertThat(intent.text(), is("Step 2 chosen."));
    }

    @Test
    public void returnsEmptyOptionalWhenIntentNotFound() {
        InMemoryIntentFlow intentFlow =
                new InMemoryIntentFlow(intentFlowFile(), new DefaultSerializer(), mock(IntentHandler.class));
        intentFlow.welcome();

        assertThat(intentFlow.nextBy(aSimpleMessageWith("3")), is(Optional.empty()));
    }


    private File intentFlowFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource("intent-flow.json");
        return new File(Objects.requireNonNull(resourceUrl).getFile());
    }

}
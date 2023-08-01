package chatbot.domain;

import static chatbot.domain.IntentFixture.aSimpleIntent;
import static org.mockito.Mockito.mock;

public class IntentsFixture {
    private IntentsFixture() {
        // fixture class
    }

    public static Intents aSimpleIntents() {
        return new Intents(aSimpleIntent(), mock(IntentFlow.class));
    }

    public static Intents aSimpleIntentsWith(IntentFlow intentFlow) {
        return new Intents(aSimpleIntent(), intentFlow);
    }
}
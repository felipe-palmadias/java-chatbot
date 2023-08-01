package chatbot.domain;

import static org.mockito.Mockito.mock;

public class IntentFixture {

    private IntentFixture() {
        // fixture class
    }

    public static Intent aSimpleIntent() {
        return new Intent("aName", "aText", mock(IntentHandler.class));
    }

    public static Intent aSimpleIntentWith(IntentHandler handler) {
        return new Intent("aName", "aText", handler);
    }
}
package ut.chatbot.domain;

import chatbot.domain.Intent;
import chatbot.domain.IntentHandler;
import chatbot.domain.User;
import org.junit.Test;

import static chatbot.domain.IntentFixture.aSimpleIntentWith;
import static chatbot.domain.UserFixture.aSimpleUser;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IntentTest {

    @Test
    public void respondsIntent() {
        IntentHandler handler = mock(IntentHandler.class);
        Intent intent = aSimpleIntentWith(handler);
        User user = aSimpleUser();

        intent.respond(user);

        verify(handler).respond(intent, user);
    }
}
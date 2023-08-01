package ut.chatbot.domain;

import chatbot.domain.*;
import org.junit.Test;

import static chatbot.domain.ConversationFixture.aSimpleConversationWith;
import static chatbot.domain.IntentsFixture.aSimpleIntentsWith;
import static chatbot.domain.MessageFixture.aSimpleMessage;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConversationTest {

    @Test
    public void initializesConversation() {
        Intent welcomeIntent = mock(Intent.class);
        IntentFlow intentFlow = mock(IntentFlow.class);
        when(intentFlow.welcome()).thenReturn(welcomeIntent);
        Conversation conversation = aSimpleConversationWith(aSimpleIntentsWith(intentFlow));

        conversation.initialization();

        verify(welcomeIntent).respond(any(User.class));
    }

    @Test
    public void receivesMessage() {
        Intents intents = mock(Intents.class);
        Message message = aSimpleMessage();
        Intent intent = mock(Intent.class);
        Conversation conversation = aSimpleConversationWith(intents);
        when(intents.selectBy(message)).thenReturn(intent);

        conversation.receive(message);

        verify(intent).respond(any(User.class));
    }
}
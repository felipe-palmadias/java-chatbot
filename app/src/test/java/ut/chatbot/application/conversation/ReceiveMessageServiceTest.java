package ut.chatbot.application.conversation;

import chatbot.application.TransactionalContext;
import chatbot.application.conversation.ReceiveMessageService;
import chatbot.domain.ChatBot;
import chatbot.domain.Conversation;
import chatbot.domain.Message;
import chatbot.domain.User;
import org.junit.Test;

import static chatbot.domain.MessageFixture.aSimpleMessage;
import static chatbot.domain.UserFixture.aSimpleUser;
import static org.mockito.Mockito.*;

public class ReceiveMessageServiceTest {

    @Test
    public void receivesMessage() {
        User user = aSimpleUser();
        Message message = aSimpleMessage();
        Conversation conversation = mock(Conversation.class);
        when(chatBot.obtain(user)).thenReturn(conversation);

        service.receive(user, message);

        verify(conversation).receive(message);
    }

    private final TransactionalContext context = Runnable::run;
    private final ChatBot chatBot = mock(ChatBot.class);
    private final ReceiveMessageService service = new ReceiveMessageService(context, chatBot);

}
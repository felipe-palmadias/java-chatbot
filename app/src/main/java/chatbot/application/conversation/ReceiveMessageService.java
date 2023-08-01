package chatbot.application.conversation;

import chatbot.application.TransactionalContext;
import chatbot.domain.ChatBot;
import chatbot.domain.Conversation;
import chatbot.domain.Message;
import chatbot.domain.User;

public class ReceiveMessageService {

    private final TransactionalContext context;
    private final ChatBot chatBot;

    public ReceiveMessageService(TransactionalContext context, ChatBot chatBot) {
        this.context = context;
        this.chatBot = chatBot;
    }

    public void receive(User user, Message message) {
        context.execute(() -> {
            Conversation conversation = chatBot.obtain(user);
            conversation.receive(message);
        });
    }
}

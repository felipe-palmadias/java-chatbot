package chatbot.domain;

public class ChatBot {

    private final ConversationRepository conversationRepository;
    private final ConversationFactory conversationFactory;

    public ChatBot(ConversationRepository conversationRepository, ConversationFactory conversationFactory) {
        this.conversationRepository = conversationRepository;
        this.conversationFactory = conversationFactory;
    }

    public Conversation obtain(User user) {
        return conversationRepository
                .findBy(user, Conversation.Status.ACTIVE)
                .orElse(conversationFactory.createWith(user));
    }
}

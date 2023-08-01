package chatbot.domain;

public class ConversationFactory {
    private final Intents intents;
    private final ConversationRepository conversationRepository;
    private final Clock clock;


    public ConversationFactory(Intents intents, ConversationRepository conversationRepository, Clock clock) {
        this.intents = intents;
        this.conversationRepository = conversationRepository;
        this.clock = clock;
    }

    public Conversation createWith(User user) {
        Conversation conversation = new Conversation(user, intents, clock);
        conversation.initialization();
        conversationRepository.insert(conversation);
        return conversation;
    }
}

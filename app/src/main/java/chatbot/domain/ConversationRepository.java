package chatbot.domain;

import java.util.Optional;

public interface ConversationRepository {
    Optional<Conversation> findBy(User user, Conversation.Status status);
    void insert(Conversation conversation);
}

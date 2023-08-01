package chatbot.domain;

import java.util.Optional;

public interface IntentFlow {
    Intent welcome();
    Optional<Intent> nextBy(Message message);
}

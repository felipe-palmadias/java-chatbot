package chatbot.domain;

import java.util.Date;

public class Conversation {

    private final User user;
    private final Intents intents;
    private final Clock clock;
    private Date initDate;

    Conversation(User user, Intents intents, Clock clock) {
        this.user = user;
        this.intents = intents;
        this.clock = clock;
    }

    public void initialization() {
        if (initDate != null) {
            throw new IllegalStateException();
        }
        initDate = clock.now();
        intents.welcome().respond(user);
    }

    public void receive(Message message) {
        if (initDate == null) {
            initDate = clock.now();
        }
        intents.selectBy(message).respond(user);
    }

    public enum Status {
        ACTIVE
    }
}

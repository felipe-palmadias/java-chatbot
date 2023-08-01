package chatbot.domain;

import static chatbot.domain.ClockFixture.aSimpleClock;
import static chatbot.domain.IntentsFixture.aSimpleIntents;
import static chatbot.domain.UserFixture.aSimpleUser;

public class ConversationFixture {

    private ConversationFixture() {
        // fixture class
    }

    public static Conversation aSimpleConversation() {
        return new Conversation(aSimpleUser(), aSimpleIntents(), aSimpleClock());
    }

    public static Conversation aSimpleConversationWith(Intents intents) {
        return new Conversation(aSimpleUser(), intents, aSimpleClock());
    }
}
package chatbot.domain;

public interface IntentHandler {
    void respond(Intent intent, User user);
}

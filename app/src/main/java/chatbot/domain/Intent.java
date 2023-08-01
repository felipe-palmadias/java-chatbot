package chatbot.domain;

public class Intent {

    private final String name;
    private final String text;
    private final IntentHandler intentHandler;

    public Intent(String name, String text, IntentHandler intentHandler) {
        this.name = name;
        this.text = text;
        this.intentHandler = intentHandler;
    }

    public void respond(User user) {
        intentHandler.respond(this, user);
    }

    public String text() {
        return text;
    }
}

package chatbot.domain;

public class Intents {

    private final Intent fallback;
    private final IntentFlow flow;

    public Intents(Intent fallback, IntentFlow flow) {
        this.fallback = fallback;
        this.flow = flow;
    }

    public Intent welcome() {
        return flow.welcome();
    }

    public Intent selectBy(Message message) {
        return flow.nextBy(message).orElse(fallback);
    }
}

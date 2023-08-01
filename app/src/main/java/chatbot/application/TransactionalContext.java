package chatbot.application;

public interface TransactionalContext {
    void execute(Runnable operation);
}

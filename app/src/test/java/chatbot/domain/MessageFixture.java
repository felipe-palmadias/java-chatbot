package chatbot.domain;

public class MessageFixture {

    private MessageFixture() {
        // fixture class
    }

    public static Message aSimpleMessage() {
        return new Message("aText");
    }

    public static Message aSimpleMessageWith(String text) {
        return new Message(text);
    }
}
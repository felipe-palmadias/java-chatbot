package chatbot.domain;

public class UserFixture {
    private UserFixture() {
        // fixture class
    }

    public static User aSimpleUser() {
        return new User();
    }
}
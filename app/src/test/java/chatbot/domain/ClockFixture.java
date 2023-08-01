package chatbot.domain;

import java.util.Date;

public class ClockFixture {

    private ClockFixture() {
        // fixture class
    }

    public static Clock aSimpleClock() {
        return Date::new;
    }
}
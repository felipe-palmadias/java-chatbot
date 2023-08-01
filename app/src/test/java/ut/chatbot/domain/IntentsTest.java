package ut.chatbot.domain;

import chatbot.domain.Intent;
import chatbot.domain.IntentFlow;
import chatbot.domain.Intents;
import chatbot.domain.Message;
import org.junit.Test;

import java.util.Optional;

import static chatbot.domain.IntentFixture.aSimpleIntent;
import static chatbot.domain.MessageFixture.aSimpleMessage;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IntentsTest {


    @Test
    public void selectsNextIntentByMessage() {
        Intents intents = new Intents(aSimpleIntent(), flow);
        Intent intent = mock(Intent.class);
        when(flow.nextBy(any(Message.class))).thenReturn(Optional.of(intent));

        assertThat(intents.selectBy(aSimpleMessage()), is(intent));
    }

    private IntentFlow flow = mock(IntentFlow.class);

}
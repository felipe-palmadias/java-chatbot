package ut.chatbot.domain;

import chatbot.domain.*;
import org.junit.Test;

import static chatbot.domain.IntentFixture.aSimpleIntent;
import static chatbot.domain.UserFixture.aSimpleUser;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class ConversationFactoryTest {

    @Test
    public void createsNewConversation() {
        User user = aSimpleUser();
        when(intents.welcome()).thenReturn(aSimpleIntent());

        Conversation conversation = factory.createWith(user);

        verify(conversationRepository).insert(conversation);
    }

    private final Intents intents = mock(Intents.class);
    private final ConversationRepository conversationRepository = mock(ConversationRepository.class);
    private final Clock clock = mock(Clock.class);
    private final ConversationFactory factory = new ConversationFactory(intents, conversationRepository, clock);

}
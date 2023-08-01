package ut.chatbot.domain;

import chatbot.domain.*;
import org.junit.Test;

import java.util.Optional;

import static chatbot.domain.ConversationFixture.aSimpleConversation;
import static chatbot.domain.UserFixture.aSimpleUser;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatBotTest {

    @Test
    public void readsActiveConversation() {
        User user = aSimpleUser();
        Optional<Conversation> conversation = Optional.of(aSimpleConversation());
        when(conversationRepository.findBy(user, Conversation.Status.ACTIVE)).thenReturn(conversation);

        assertThat(chatBot.obtain(user), is(conversation.orElseThrow()));
    }

    @Test
    public void createsActiveConversation() {
        User user = aSimpleUser();
        Conversation conversation = aSimpleConversation();
        when(conversationRepository.findBy(user, Conversation.Status.ACTIVE)).thenReturn(Optional.empty());
        when(conversationFactory.createWith(user)).thenReturn(conversation);

        assertThat(chatBot.obtain(user), is(conversation));
    }

    private final ConversationRepository conversationRepository = mock(ConversationRepository.class);
    private final ConversationFactory conversationFactory = mock(ConversationFactory.class);
    private final ChatBot chatBot = new ChatBot(conversationRepository, conversationFactory);

}
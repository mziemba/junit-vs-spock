package tech.viacom.junitvsspock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * We have a {@link Publisher} and subscribers, that can subscribe to a specific publisher.
 */
public class J03_MockingTest {

    private Publisher publisher;
    private Subscriber subscriber1;
    private Subscriber subscriber2;
    @Captor private ArgumentCaptor<String> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        publisher = new Publisher();
        subscriber1 = mock(Subscriber.class);
        subscriber2 = mock(Subscriber.class);
        publisher.getSubscribers().add(subscriber1);
        publisher.getSubscribers().add(subscriber2);
    }

    @Test
    public void argumentMatcher() {
        // we want to check, if receive on subscriber2 was called with argument of length > 3
        publisher.send("hello");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(subscriber1).receive("hello");
        verify(subscriber2).receive(captor.capture());

        assertThat(captor.getValue().length()).isGreaterThan(10);
    }

    @Test
    public void testInOrder() {
        // when
        publisher.send("goodbye");
        publisher.send("hello");

        // then
        InOrder inOrder = inOrder(subscriber1);
        inOrder.verify(subscriber1).receive("hello");
        inOrder.verify(subscriber1).receive("goodbye");
    }

    @Test
    public void testReturningValues() {
        // given
        given(subscriber1.receive("hello")).willReturn("ok");

        publisher.send("goodbye");
        verify(subscriber1).receive("hello");
    }
}

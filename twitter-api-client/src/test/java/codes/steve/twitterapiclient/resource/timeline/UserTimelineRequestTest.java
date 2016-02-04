package codes.steve.twitterapiclient.resource.timeline;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTimelineRequestTest {

    private static final String USER = "sb_io";
    private static final String ACCESS_TOKEN = "MY_ACCESS_TOKEN";
    private static final String USER_TIMELINE_PATH = "/1.1/statuses/user_timeline.json";

    @Mock
    private WebTarget mockBaseWebTarget;

    @Mock
    private WebTarget mockUserTimelineWebTarget;

    @Mock
    private Invocation.Builder mockInvocationBuilder;

    @Before
    public void setUp() {
        when(mockBaseWebTarget.path(anyString())).thenReturn(mockUserTimelineWebTarget);
        when(mockUserTimelineWebTarget.queryParam(anyString(), anyString())).thenReturn(mockUserTimelineWebTarget);
        when(mockUserTimelineWebTarget.request()).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.header(anyString(), anyString())).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.accept(any(MediaType.class))).thenReturn(mockInvocationBuilder);
    }

    @Test
    public void shouldCreateAUserTimelineRequestWithCorrectParameters() {
        new UserTimelineRequest(mockBaseWebTarget, ACCESS_TOKEN, USER);

        verify(mockUserTimelineWebTarget).request();
        verify(mockBaseWebTarget).path(USER_TIMELINE_PATH);
        verify(mockUserTimelineWebTarget).queryParam(eq("screen_name"), eq(USER));
        verify(mockInvocationBuilder).header(eq("Authorization"), eq("Bearer " + ACCESS_TOKEN));
        verify(mockInvocationBuilder).accept(eq(APPLICATION_JSON_TYPE));
    }

    @Test
    public void shouldSubmitRequest() {
        Tweet[] expectedTweets = new Tweet[0];
        when(mockInvocationBuilder.get(any(Class.class))).thenReturn(expectedTweets);

        UserTimelineRequest request = new UserTimelineRequest(mockBaseWebTarget, ACCESS_TOKEN, USER);
        Tweet[] response = request.submit();

        Assertions.assertThat(response).isEqualTo(expectedTweets);

        verify(mockInvocationBuilder).get(Tweet[].class);
    }
}
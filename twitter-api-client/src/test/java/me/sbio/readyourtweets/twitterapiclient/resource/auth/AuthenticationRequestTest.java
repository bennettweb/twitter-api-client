package me.sbio.readyourtweets.twitterapiclient.resource.auth;

import me.sbio.readyourtweets.twitterapiclient.resource.EntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationRequestTest {

    private static final String ENCODED_BEARER_TOKEN = "TWITTER_ENCODED_TOKEN";
    private static final Entity<String> ENTITY = Entity.entity("entity", APPLICATION_FORM_URLENCODED_TYPE);
    private static final String OAUTH_TOKEN_PATH = "oauth2/token";


    @Mock
    private WebTarget mockBaseWebTarget;

    @Mock
    private WebTarget mockAuthenticationWebTarget;

    @Mock
    private Invocation.Builder mockInvocationBuilder;

    @Mock
    private EntityFactory entityFactory;

    @Before
    public void setUp() {
        when(mockBaseWebTarget.path(anyString())).thenReturn(mockAuthenticationWebTarget);
        when(mockAuthenticationWebTarget.request()).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.header(anyString(), anyString())).thenReturn(mockInvocationBuilder);
        when(entityFactory.newAuthenticationEntity()).thenReturn(ENTITY);
    }

    @Test
    public void shouldCreateAnAuthenticationRequestWithCorrectParameters() {
        new AuthenticationRequest(mockBaseWebTarget, entityFactory, ENCODED_BEARER_TOKEN);

        verify(mockBaseWebTarget).path(OAUTH_TOKEN_PATH);
        verify(mockInvocationBuilder).header(eq("Authorization"), eq("Basic " + ENCODED_BEARER_TOKEN));
        verify(mockAuthenticationWebTarget).request();

        verifyNoMoreInteractions(mockBaseWebTarget, mockInvocationBuilder, mockAuthenticationWebTarget);
    }

    @Test
    public void shouldCreateAnAuthenticationRequestWithAuthenticationEntity() {
        AuthenticationRequest request = new AuthenticationRequest(mockBaseWebTarget, entityFactory, ENCODED_BEARER_TOKEN);

        assertThat(request.getEntity()).isEqualTo(ENTITY);
    }

    @Test
    public void shouldSubmitRequest() {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        when(mockInvocationBuilder.post(any(Entity.class), any(Class.class))).thenReturn(authenticationResponse);

        AuthenticationRequest request = new AuthenticationRequest(mockBaseWebTarget, entityFactory, ENCODED_BEARER_TOKEN);

        AuthenticationResponse response = request.submit();
        assertThat(response).isEqualTo(response);

        verify(mockInvocationBuilder).post(eq(ENTITY), eq(AuthenticationResponse.class));
    }
}
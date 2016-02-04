package codes.steve.twitterapiclient.resource.auth;

import codes.steve.twitterapiclient.resource.EntityFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

public class AuthenticationRequest {

    private static final String OAUTH_PATH = "oauth2/token";
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE_FORMAT = "Basic %s";

    private final Invocation.Builder request;
    private final Entity<String> authenticationEntity;

    public AuthenticationRequest(WebTarget baseWebTarget, EntityFactory entityFactory, String encodedBearerToken) {
        WebTarget webTarget = baseWebTarget.path(OAUTH_PATH);
        request = webTarget.request().
                header(AUTHORIZATION_HEADER_KEY, buildAuthorizationHeader(encodedBearerToken));
        authenticationEntity = entityFactory.newAuthenticationEntity();
    }

    private String buildAuthorizationHeader(String encodedBearerToken) {
        return String.format(AUTHORIZATION_HEADER_VALUE_FORMAT, encodedBearerToken);
    }

    public AuthenticationResponse submit() {
        return request.post(authenticationEntity, AuthenticationResponse.class);
    }

    public Entity<String> getEntity() {
        return authenticationEntity;
    }
}

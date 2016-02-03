package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import me.sbio.twitterstub.mappings.TwitterRequest;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static me.sbio.twitterstub.AuthorizationTokenType.BASIC;
import static me.sbio.twitterstub.ContentType.APPLICATION_FORM;

class AuthenticationRequest extends TwitterRequest {

    private static final String OAUTH2_TOKEN_PATH = "/oauth2/token";

    public AuthenticationRequest() {
        super(RequestMethod.POST, urlEqualTo(OAUTH2_TOKEN_PATH));
        withContentType(APPLICATION_FORM);
        withRequestBody(equalTo("grant_type=client_credentials"));
    }

    public AuthenticationRequest withEncodedBearerTokenMatching(String encodedBearerToken) {
        withAuthorizationHeader(BASIC, encodedBearerToken);
        return this;
    }
}

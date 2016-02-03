package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

public class MatchAllAuthenticationMapping extends AuthenticationMapping {

    public MatchAllAuthenticationMapping() {
        super(".*");
    }

    @Override
    public ResponseDefinitionBuilder response() {
        return new InvalidAuthenticationResponse();
    }
}

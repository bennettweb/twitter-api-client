package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

class InvalidAuthenticationResponse extends ResponseDefinitionBuilder {

    public InvalidAuthenticationResponse() {
        withStatus(403);
    }
}

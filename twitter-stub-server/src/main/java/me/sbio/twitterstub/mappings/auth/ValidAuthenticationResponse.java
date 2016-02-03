package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

import static me.sbio.twitterstub.TwitterStubServerParams.ACCESS_TOKEN;

class ValidAuthenticationResponse extends ResponseDefinitionBuilder {
    public ValidAuthenticationResponse() {
        withStatus(200);
        withHeader("Content-Type", "application/json; charset=UTF-8");
        withBody("{\"token_type\":\"bearer\",\"access_token\":\"" + ACCESS_TOKEN + "\"}");
    }
}

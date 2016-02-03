package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import me.sbio.twitterstub.mappings.Mapping;

public abstract class AuthenticationMapping implements Mapping {

    private final String encodedBearerToken;

    public AuthenticationMapping(String encodedBearerToken) {
        this.encodedBearerToken = encodedBearerToken;
    }

    @Override
    public MappingBuilder request() {
        return new AuthenticationRequest().withEncodedBearerTokenMatching(encodedBearerToken);
    }

}

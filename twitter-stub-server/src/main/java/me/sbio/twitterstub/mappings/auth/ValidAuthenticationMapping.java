package me.sbio.twitterstub.mappings.auth;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import codes.steve.twitterapiclient.commons.util.BearerTokenCreationException;
import codes.steve.twitterapiclient.commons.util.TwitterKeyUtil;

public class ValidAuthenticationMapping extends AuthenticationMapping {

    public ValidAuthenticationMapping(String consumerKey, String consumerSecret, int priority) throws BearerTokenCreationException {
        super(new TwitterKeyUtil().createEncodedBearerToken(consumerKey, consumerSecret));
    }

    @Override
    public ResponseDefinitionBuilder response() {
        return new ValidAuthenticationResponse();
    }
}

package me.sbio.twitterstub.mappings;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import me.sbio.twitterstub.AuthorizationTokenType;
import me.sbio.twitterstub.ContentType;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;

public class TwitterRequest extends MappingBuilder{

    public TwitterRequest(RequestMethod method, UrlMatchingStrategy urlMatchingStrategy) {
        super(method, urlMatchingStrategy);
    }

    public TwitterRequest withContentType(ContentType contentType) {
        withHeader("Content-Type", equalTo(contentType.value()));
        return this;
    }

    public TwitterRequest withAuthorizationHeader(AuthorizationTokenType authorizationTokenType, String tokenPattern) {
        withHeader("Authorization", matching(authorizationTokenType.value() + " " + tokenPattern));
        return this;
    }
}

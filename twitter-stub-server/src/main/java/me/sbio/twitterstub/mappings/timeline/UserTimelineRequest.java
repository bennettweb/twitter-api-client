package me.sbio.twitterstub.mappings.timeline;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.http.RequestMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static me.sbio.twitterstub.TwitterStubServerParams.ACCESS_TOKEN;

class UserTimelineRequest extends MappingBuilder {

    public UserTimelineRequest(String screenName) {
        super(RequestMethod.GET, urlMatching("/1.1/statuses/user_timeline.json\\?screen_name=" + screenName));
        withHeader("Authorization", equalTo("Bearer " + ACCESS_TOKEN));
    }
}

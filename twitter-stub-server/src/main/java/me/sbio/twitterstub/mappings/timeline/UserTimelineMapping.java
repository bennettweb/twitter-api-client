package me.sbio.twitterstub.mappings.timeline;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import me.sbio.twitterstub.mappings.Mapping;

import java.util.List;

public class UserTimelineMapping implements Mapping {

    private final String screenname;
    private final List<String> tweets;

    public UserTimelineMapping(String screenname, List<String> tweets) {
        this.screenname = screenname;
        this.tweets = tweets;
    }

    @Override
    public MappingBuilder request() {
        return new UserTimelineRequest(screenname);
    }

    @Override
    public ResponseDefinitionBuilder response() {
        return new UserTimelineResponse().withTweets(tweets);
    }
}

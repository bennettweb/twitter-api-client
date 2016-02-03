package me.sbio.twitterstub.mappings.timeline;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.google.common.collect.Lists;
import me.sbio.twitterstub.mappings.Mapping;

import java.util.List;

public class DefaultUserTimelineMapping implements Mapping {

    @Override
    public MappingBuilder request() {
        return new UserTimelineRequest("[A-Za-z0-9_]{1,15}");
    }

    @Override
    public ResponseDefinitionBuilder response() {
        return new UserTimelineResponse().withTweets(aListOfTweets(20));
    }

    private List<String> aListOfTweets(int count) {
        List<String> tweets = Lists.newLinkedList();

        for (int i=0; i<count; i++) {
            tweets.add("Tweet number " + i);
        }

        return tweets;
    }
}

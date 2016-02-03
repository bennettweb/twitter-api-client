package me.sbio.twitterstub.mappings.timeline;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

import static com.google.common.io.ByteStreams.toByteArray;

class UserTimelineResponse extends ResponseDefinitionBuilder {

    public UserTimelineResponse() {
        withHeader("Content-Type", "application/json");
    }

    public UserTimelineResponse withTweets(List<String> textForTweets) {
        List<String> formattedTweets = Lists.newLinkedList();

        String tweetTemplate = tweetTemplate();
        for(String text : textForTweets) {
            String tweet = String.format(tweetTemplate, text);
            formattedTweets.add(tweet);
        }

        String twitterResponse = "[" + Joiner.on(",").join(formattedTweets) + "]";
        withBody(twitterResponse);
        return this;
    }

    private String tweetTemplate() {
        try {
            byte[] templateBytes = toByteArray(getClass().getResourceAsStream("/tweet.template"));
            return new String(templateBytes);
        } catch (IOException e) {
            throw new IllegalStateException("failed to read tweet.template", e);
        }
    }
}

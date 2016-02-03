package me.sbio.readyourtweets.twitterapiclient;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import me.sbio.readyourtweets.commons.config.TwitterConfig;
import me.sbio.readyourtweets.commons.util.BearerTokenCreationException;
import me.sbio.readyourtweets.twitterapiclient.resource.RequestFactory;
import me.sbio.readyourtweets.twitterapiclient.resource.auth.AuthenticationRequest;
import me.sbio.readyourtweets.twitterapiclient.resource.auth.AuthenticationResponse;
import me.sbio.readyourtweets.twitterapiclient.resource.timeline.Tweet;
import me.sbio.readyourtweets.twitterapiclient.resource.timeline.UserTimelineRequest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TwitterApi {

    private final RequestFactory requestFactory;

    private String accessToken;

    public TwitterApi(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public TwitterApi(TwitterConfig twitterConfig) {
        this(new RequestFactory(twitterConfig));
    }

    public TwitterApi authenticate() throws BearerTokenCreationException {
        AuthenticationRequest request = requestFactory.authenticateRequest();
        AuthenticationResponse authenticationResponse = request.submit();
        accessToken = authenticationResponse.getAccessToken();
        return this;
    }

    public List<String> userTimeline(String user) {
        UserTimelineRequest request = requestFactory.userTimelineRequest(accessToken, user);
        Tweet[] response = request.submit();
        Collection<String> tweets = Collections2.transform(Arrays.asList(response), new Function<Tweet, String>() {
            @Override
            public String apply(Tweet tweet) {
                return tweet.getText();
            }
        });
        return Lists.newArrayList(tweets.iterator());
    }

    public boolean isAuthenticated() {
        return !Strings.isNullOrEmpty(accessToken);
    }
}

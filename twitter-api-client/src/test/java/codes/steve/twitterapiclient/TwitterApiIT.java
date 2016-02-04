package codes.steve.twitterapiclient;

import com.google.common.collect.Lists;
import codes.steve.twitterapiclient.commons.config.StubTwitterConfig;
import codes.steve.twitterapiclient.commons.config.TwitterConfig;
import codes.steve.twitterapiclient.commons.util.BearerTokenCreationException;
import me.sbio.twitterstub.TwitterStubServer;
import me.sbio.twitterstub.mappings.MappingRegistrationException;
import me.sbio.twitterstub.mappings.timeline.UserTimelineMapping;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static me.sbio.twitterstub.TwitterStubServer.authenticationMappings;
import static org.fest.assertions.Assertions.assertThat;

public class TwitterApiIT {

    private static final TwitterConfig TWITTER_CONFIG = new StubTwitterConfig();
    private static final String USER = "sb_io";
    private static TwitterStubServer TWITTER_STUB_SERVER;

    private TwitterApi twitterApi;

    @BeforeClass
    public static void setUpStubServer() throws BearerTokenCreationException, MappingRegistrationException {
        TWITTER_STUB_SERVER = new TwitterStubServer(TWITTER_CONFIG.getPort());
        TWITTER_STUB_SERVER.start();
        TWITTER_STUB_SERVER.registerMappings(authenticationMappings(TWITTER_CONFIG.getConsumerKey(), TWITTER_CONFIG.getConsumerSecret()));
    }

    @Before
    public void setUp() {
        twitterApi = new TwitterApi(TWITTER_CONFIG);
    }

    @AfterClass
    public static void stopStubServer() {
        TWITTER_STUB_SERVER.stop();
    }

    @Test
    public void shouldAuthenticateWithTwitter() throws BearerTokenCreationException {
        twitterApi.authenticate();

        assertThat(twitterApi.isAuthenticated()).isTrue();
    }

    @Test
    public void shouldRetrieveTweetsForAUser() throws BearerTokenCreationException, MappingRegistrationException {
        twitterApi.authenticate();

        List<String> expectedTweets = Lists.newArrayList("Tweet 1", "Tweet 2", "Tweet 3");

        UserTimelineMapping usertimeLineMapping = new UserTimelineMapping(USER, expectedTweets);
        TWITTER_STUB_SERVER.registerMappings(usertimeLineMapping);

        List<String> actualTweets = twitterApi.userTimeline(USER);

        assertThat(actualTweets).containsOnly(expectedTweets.toArray());
    }
}
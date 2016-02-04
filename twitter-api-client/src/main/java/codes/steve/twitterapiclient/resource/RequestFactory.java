package codes.steve.twitterapiclient.resource;

import codes.steve.twitterapiclient.commons.config.TwitterConfig;
import codes.steve.twitterapiclient.commons.util.BearerTokenCreationException;
import codes.steve.twitterapiclient.commons.util.TwitterKeyUtil;
import codes.steve.twitterapiclient.resource.auth.AuthenticationRequest;
import codes.steve.twitterapiclient.resource.timeline.UserTimelineRequest;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


public class RequestFactory {

    private final TwitterConfig twitterConfig;
    private final TwitterKeyUtil twitterKeyUtil;
    private final EntityFactory entityFactory;

    private WebTarget baseWebTarget;

    public RequestFactory(TwitterConfig twitterConfig, TwitterKeyUtil twitterKeyUtil, EntityFactory entityFactory) {
        this.twitterConfig = twitterConfig;
        this.twitterKeyUtil = twitterKeyUtil;
        this.entityFactory = entityFactory;
        this.baseWebTarget = createBaseWebTarget(twitterConfig.apiUri());
    }

    public RequestFactory(TwitterConfig twitterConfig) {
        this(twitterConfig, new TwitterKeyUtil(), new EntityFactory());
    }

    private WebTarget createBaseWebTarget(String twitterApiUrl) {
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        client.register(JacksonFeature.class);
        client.register(LoggingFilter.class);
        return client.target(twitterApiUrl);
    }

    public AuthenticationRequest authenticateRequest() throws BearerTokenCreationException {
        String encodedBearerToken = getEncodedBearerToken();
        return new AuthenticationRequest(baseWebTarget, entityFactory, encodedBearerToken);
    }

    public UserTimelineRequest userTimelineRequest(String accessToken, String user) {
        return new UserTimelineRequest(baseWebTarget, accessToken, user);
    }

    private String getEncodedBearerToken() throws BearerTokenCreationException {
        return twitterKeyUtil.createEncodedBearerToken(twitterConfig.getConsumerKey(), twitterConfig.getConsumerSecret());
    }
}

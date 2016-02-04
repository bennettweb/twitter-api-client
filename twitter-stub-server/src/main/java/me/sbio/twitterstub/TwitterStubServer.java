package me.sbio.twitterstub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import codes.steve.twitterapiclient.commons.config.TwitterConfig;
import codes.steve.twitterapiclient.commons.util.BearerTokenCreationException;
import me.sbio.twitterstub.mappings.Mapping;
import me.sbio.twitterstub.mappings.MappingRegistrationException;
import me.sbio.twitterstub.mappings.auth.MatchAllAuthenticationMapping;
import me.sbio.twitterstub.mappings.auth.ValidAuthenticationMapping;
import me.sbio.twitterstub.mappings.timeline.DefaultUserTimelineMapping;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class TwitterStubServer {

    private final WireMockServer wireMockServer;
    private final int port;

    public TwitterStubServer(int port) {
        this.port = port;
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
    }

    public void start() {
        wireMockServer.start();
    }

    public void registerMappings(Mapping... mappings) throws MappingRegistrationException {
        configureFor("localhost", port);
        for (int i=0; i<mappings.length; i++) {
            stubFor(mappings[i].request().atPriority(i+1).willReturn(mappings[i].response()));
        }
    }

    public void stop() {
        wireMockServer.stop();
    }

    public void reset() {
        WireMock.reset();
    }

    public boolean isRunning() {
        return wireMockServer.isRunning();
    }

    public void registerDefaultMappings(String consumerKey, String consumerSecret) {
        try {
            registerMappings(authenticationMappings(consumerKey, consumerSecret));
            registerMappings(defaultMappings());
        } catch (MappingRegistrationException | BearerTokenCreationException e) {
            throw new IllegalStateException("Failed to register default mappings", e);
        }
    }

    public static void main(String[] args) throws MappingRegistrationException, BearerTokenCreationException {
        TwitterConfig twitterConfig = new TwitterConfig();

        TwitterStubServer twitterStubServer = new TwitterStubServer(twitterConfig.getPort());
        twitterStubServer.start();

        twitterStubServer.registerDefaultMappings(twitterConfig.getConsumerKey(), twitterConfig.getConsumerSecret());
    }

    public static Mapping[] defaultMappings() throws BearerTokenCreationException {
        return new Mapping[] {
                new DefaultUserTimelineMapping()
        };
    }

    public static Mapping[] authenticationMappings(String consumerKey, String consumerSecret) throws BearerTokenCreationException {
        return new Mapping[]{
                new ValidAuthenticationMapping(consumerKey, consumerSecret, 1),
                new MatchAllAuthenticationMapping()
        };
    }
}

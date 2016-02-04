package codes.steve.twitterapiclient.commons.config;

import codes.steve.twitterapiclient.commons.util.UrlBuilder;

public class TwitterConfig {

    private static final String DEFAULT_BASE_URI = "https://api.twitter.com";
    private static final String DEFAULT_PORT = "443";
    private static final String DEFAULT_BASE_PATH = "/";

    public String getConsumerKey() {
        return System.getProperty("twitter.consumerKey");
    }

    public String getConsumerSecret() {
        return System.getProperty("twitter.consumerSecret");
    }

    public String getBaseUri() {
        return System.getProperty("twitter.baseUri", DEFAULT_BASE_URI);
    }

    public int getPort() {
        return Integer.valueOf(System.getProperty("twitter.port", DEFAULT_PORT));
    }

    public String getBasePath() {
        return System.getProperty("twitter.basePath", DEFAULT_BASE_PATH);
    }

    public String apiUri() {
        UrlBuilder urlBuilder = new UrlBuilder(getBaseUri());
        urlBuilder.withPort(getPort());
        urlBuilder.withPathSegment(getBasePath());
        return urlBuilder.build();
    }
}

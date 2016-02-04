package codes.steve.twitterapiclient.commons.config;

public class StubTwitterConfig extends TwitterConfig {

    @Override
    public String getConsumerKey() {
        return "CONSUMER_KEY";
    }

    @Override
    public String getConsumerSecret() {
        return "CONSUMER_SECRET";
    }

    @Override
    public String getBaseUri() {
        return "http://localhost";
    }

    @Override
    public int getPort() {
        return 8888;
    }

    @Override
    public String getBasePath() {
        return "/";
    }
}

package me.sbio.readyourtweets.contract;

import com.jayway.restassured.RestAssured;
import me.sbio.readyourtweets.commons.config.TwitterConfig;
import me.sbio.readyourtweets.commons.util.TwitterKeyUtil;
import org.junit.Before;

public abstract class ContractIT {

    private final TwitterConfig twitterConfig;
    private final TwitterKeyUtil twitterKeyUtil;

    public ContractIT() {
        twitterConfig = new TwitterConfig();
        twitterKeyUtil = new TwitterKeyUtil();
    }

    public TwitterConfig getTwitterConfig() {
        return twitterConfig;
    }

    public TwitterKeyUtil getTwitterKeyUtil() {
        return twitterKeyUtil;
    }

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = twitterConfig.getBaseUri();
        RestAssured.port = twitterConfig.getPort();
        RestAssured.basePath = twitterConfig.getBasePath();
    }
}

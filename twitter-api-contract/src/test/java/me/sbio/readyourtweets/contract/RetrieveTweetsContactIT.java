package me.sbio.readyourtweets.contract;

import me.sbio.readyourtweets.commons.util.BearerTokenCreationException;
import me.sbio.readyourtweets.spec.RetrieveUserTweetsRequestSpec;
import me.sbio.readyourtweets.spec.TwitterAppAuthenticationRequestSpec;
import me.sbio.readyourtweets.twitterapiclient.resource.auth.AuthenticationResponse;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

public class RetrieveTweetsContactIT extends ContractIT {

    private static final String SCREEN_NAME = "twitterapi";

    @Test
    public void shouldReturnOkStatusCodeForRetrieveTweetsRequest() throws BearerTokenCreationException {
        String accessToken = generateAccessToken();

        RetrieveUserTweetsRequestSpec userTweetsRequestSpec = aRetrieveTweetRequestSpecification(accessToken, SCREEN_NAME);

        given().
                spec(userTweetsRequestSpec.asRequestSpecification()).
        when().
                get(userTweetsRequestSpec.path()).
        then().
                statusCode(200).
                contentType(JSON);
    }

    @Test
    public void shouldReturnTextForEveryTweetReturned() throws BearerTokenCreationException {
        String accessToken = generateAccessToken();

        RetrieveUserTweetsRequestSpec retrieveUserTweetsRequestSpec = aRetrieveTweetRequestSpecification(accessToken, SCREEN_NAME);

        given().
                spec(retrieveUserTweetsRequestSpec.asRequestSpecification()).
        when().
                get(retrieveUserTweetsRequestSpec.path()).
        then().
                body("text", notNullValue()).
                body("text", everyItem(not("")));
    }

    private String generateAccessToken() throws BearerTokenCreationException {
        String encodedBearerToken = generateEncodedBearerToken();
        return authenticateApplication(encodedBearerToken);
    }

    private String authenticateApplication(String encodedBearerToken) {
        TwitterAppAuthenticationRequestSpec authenticationRequestSpec = new TwitterAppAuthenticationRequestSpec(encodedBearerToken);

        AuthenticationResponse authenticationResponse = given().
                    spec(authenticationRequestSpec.asRequestSpecification()).
                when().
                    post(authenticationRequestSpec.path()).as(AuthenticationResponse.class);

        return authenticationResponse.getAccessToken();
    }

    private String generateEncodedBearerToken() throws BearerTokenCreationException {
        String consumerKey = getTwitterConfig().getConsumerKey();
        String consumerSecret = getTwitterConfig().getConsumerSecret();

        return getTwitterKeyUtil().createEncodedBearerToken(consumerKey, consumerSecret);
    }

    private RetrieveUserTweetsRequestSpec aRetrieveTweetRequestSpecification(String accessToken, String screenName) {
        return new RetrieveUserTweetsRequestSpec(accessToken).withScreenName(screenName);
    }

}

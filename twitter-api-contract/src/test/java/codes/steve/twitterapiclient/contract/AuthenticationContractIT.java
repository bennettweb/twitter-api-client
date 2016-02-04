package codes.steve.twitterapiclient.contract;

import codes.steve.twitterapiclient.commons.util.BearerTokenCreationException;
import codes.steve.twitterapiclient.spec.TwitterAppAuthenticationRequestSpec;
import codes.steve.twitterapiclient.spec.TwitterRequestSpec;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class AuthenticationContractIT extends ContractIT {

    @Test
    public void shouldObtainAnAccessTokenForValidConsumerKeyAndSecret() throws BearerTokenCreationException {
        String consumerKey = getTwitterConfig().getConsumerKey();
        String consumerSecret = getTwitterConfig().getConsumerSecret();

        String encodedBearerToken = getTwitterKeyUtil().createEncodedBearerToken(consumerKey, consumerSecret);

        TwitterRequestSpec authenticationRequestSpecification = anAuthenticationRequestSpecification(encodedBearerToken);

        given().
                spec(authenticationRequestSpecification.asRequestSpecification()).
        when().
                post(authenticationRequestSpecification.path()).
        then().
                statusCode(200).
                contentType(JSON).
                body("token_type", is("bearer")).
                body("access_token", not(isEmptyOrNullString()));
    }

    @Test
    public void shouldReturnForbiddenForInvalidConsumerKey() throws BearerTokenCreationException {
        String consumerKey = "INVALID_KEY";
        String consumerSecret = getTwitterConfig().getConsumerSecret();

        String encodedBearerToken = getTwitterKeyUtil().createEncodedBearerToken(consumerKey, consumerSecret);

        TwitterRequestSpec authenticationRequestSpecification = anAuthenticationRequestSpecification(encodedBearerToken);

        given().
                spec(authenticationRequestSpecification.asRequestSpecification()).
        when().
                post(authenticationRequestSpecification.path()).
        then().
                statusCode(403);

    }

    @Test
    public void shouldReturnForbiddenForInvalidConsumerSecret() throws BearerTokenCreationException {
        String consumerKey = getTwitterConfig().getConsumerKey();
        String consumerSecret = "INVALID_SECRET";

        String encodedBearerToken = getTwitterKeyUtil().createEncodedBearerToken(consumerKey, consumerSecret);

        TwitterRequestSpec authenticationRequestSpecification = anAuthenticationRequestSpecification(encodedBearerToken);

        given().
                spec(authenticationRequestSpecification.asRequestSpecification()).
        when().
                post(authenticationRequestSpecification.path()).
        then().
                statusCode(403);

    }

    private TwitterRequestSpec anAuthenticationRequestSpecification(String encodedBearerToken) {
        return new TwitterAppAuthenticationRequestSpec(encodedBearerToken);
    }

}

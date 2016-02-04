package codes.steve.twitterapiclient.spec;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

import static java.lang.String.format;

public class TwitterAppAuthenticationRequestSpec implements TwitterRequestSpec {

    private static final String PATH = "/oauth2/token";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE_FORMAT = "Basic %s";
    private static final String AUTHORIZATION_BODY = "grant_type=client_credentials";

    private RequestSpecBuilder requestSpecBuilder;

    public TwitterAppAuthenticationRequestSpec(String encodedBearerToken) {
        requestSpecBuilder = new RequestSpecBuilder()
                .setContentType(CONTENT_TYPE)
                .addHeader(AUTHORIZATION_HEADER_KEY, buildAuthenticationHeaderValue(encodedBearerToken))
                .setBody(AUTHORIZATION_BODY);
    }

    private String buildAuthenticationHeaderValue(String encodedBearerToken) {
        return format(AUTHORIZATION_HEADER_VALUE_FORMAT, encodedBearerToken);
    }

    public RequestSpecification asRequestSpecification() {
        return requestSpecBuilder.build();
    }

    @Override
    public String path() {
        return PATH;
    }
}

package me.sbio.readyourtweets.spec;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

import static java.lang.String.format;

public class RetrieveUserTweetsRequestSpec implements TwitterRequestSpec {

    private static final String USER_TIMELINE_PATH = "/1.1/statuses/user_timeline.json";
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String SCREEN_NAME_PARAM = "screen_name";

    private RequestSpecBuilder requestSpecBuilder;

    public RetrieveUserTweetsRequestSpec(String accessToken) {
        this.requestSpecBuilder = new RequestSpecBuilder()
                .addHeader(AUTHORIZATION_HEADER_KEY, buildAuthorizationHeaderValue(accessToken));
    }

    private String buildAuthorizationHeaderValue(String accessToken) {
        return format("Bearer %s", accessToken);
    }

    public RetrieveUserTweetsRequestSpec withScreenName(String screenName) {
        requestSpecBuilder.addQueryParam(SCREEN_NAME_PARAM, screenName);
        return this;
    }

    @Override
    public RequestSpecification asRequestSpecification() {
        return requestSpecBuilder.build();
    }

    @Override
    public String path() {
        return USER_TIMELINE_PATH;
    }
}

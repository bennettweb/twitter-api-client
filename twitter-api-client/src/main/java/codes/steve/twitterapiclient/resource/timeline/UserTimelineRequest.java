package codes.steve.twitterapiclient.resource.timeline;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

public class UserTimelineRequest {

    private final Invocation.Builder request;

    public UserTimelineRequest(WebTarget baseWebTarget, String accessToken, String user) {
        WebTarget userTimelineWebTarget = baseWebTarget.path("/1.1/statuses/user_timeline.json");
        request = userTimelineWebTarget.queryParam("screen_name", user).
                request().
                accept(APPLICATION_JSON_TYPE).
                header("Authorization", "Bearer " + accessToken);
    }

    public Tweet[] submit() {
        return request.get(Tweet[].class);
    }
}

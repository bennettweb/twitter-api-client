package codes.steve.twitterapiclient.resource.timeline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

    @JsonProperty("text")
    private String text;

    public String getText() {
        return text;
    }
}

package me.sbio.twitterstub;

public enum AuthorizationTokenType {

    BEARER("Bearer"),
    BASIC("Basic");

    private final String value;

    private AuthorizationTokenType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

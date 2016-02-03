package me.sbio.twitterstub;

public enum ContentType {

    APPLICATION_FORM("application/x-www-form-urlencoded; charset=UTF-8");

    private final String value;

    private ContentType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

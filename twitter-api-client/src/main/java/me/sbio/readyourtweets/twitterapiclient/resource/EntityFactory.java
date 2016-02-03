package me.sbio.readyourtweets.twitterapiclient.resource;

import javax.ws.rs.client.Entity;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;

public class EntityFactory {

    private static final String AUTHENTICATION_REQUEST_BODY = "grant_type=client_credentials";
    private static final String CHARSET = "UTF-8";

    public Entity<String> newAuthenticationEntity() {
        return Entity.entity(AUTHENTICATION_REQUEST_BODY, APPLICATION_FORM_URLENCODED_TYPE.withCharset(CHARSET));
    }
}

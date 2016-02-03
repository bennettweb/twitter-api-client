package me.sbio.readyourtweets.twitterapiclient.resource;

import org.junit.Test;

import javax.ws.rs.client.Entity;

import static org.fest.assertions.Assertions.assertThat;


public class EntityFactoryTest {

    private final EntityFactory entityFactory = new EntityFactory();

    @Test
    public void shouldReturnANewAuthenticationEntity() {
        Entity<String> entity = entityFactory.newAuthenticationEntity();

        assertThat(entity.getEntity()).isEqualTo("grant_type=client_credentials");
        assertThat(entity.getMediaType().toString()).isEqualTo("application/x-www-form-urlencoded; charset=UTF-8");
    }
}
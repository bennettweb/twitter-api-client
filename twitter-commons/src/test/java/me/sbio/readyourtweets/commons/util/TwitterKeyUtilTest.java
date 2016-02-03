package me.sbio.readyourtweets.commons.util;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class TwitterKeyUtilTest {

    private static final String SAMPLE_CONSUMER_KEY = "xvz1evFS4wEEPTGEFPHBog";
    private static final String SAMPLE_CONSUMER_SECRET = "L8qq9PZyRg6ieKGEKhZolGC0vJWLw8iEJ88DRdyOg";

    private TwitterKeyUtil twitterKeyUtil;

    @Before
    public void setUp() {
        twitterKeyUtil = new TwitterKeyUtil();
    }

    @Test
    public void shouldGenerateAnEncodedBearerTokenForAConsumerKeyAndSecret() throws BearerTokenCreationException {
        String encodedBearerToken = twitterKeyUtil.createEncodedBearerToken(SAMPLE_CONSUMER_KEY, SAMPLE_CONSUMER_SECRET);

        assertThat(encodedBearerToken).isEqualTo("eHZ6MWV2RlM0d0VFUFRHRUZQSEJvZzpMOHFxOVBaeVJnNmllS0dFS2hab2xHQzB2SldMdzhpRUo4OERSZHlPZw==");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullConsumerKey() throws BearerTokenCreationException {
        twitterKeyUtil.createEncodedBearerToken(null, SAMPLE_CONSUMER_SECRET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForEmptyConsumerKey() throws BearerTokenCreationException {
        twitterKeyUtil.createEncodedBearerToken("", SAMPLE_CONSUMER_SECRET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullConsumerSecret() throws BearerTokenCreationException {
        twitterKeyUtil.createEncodedBearerToken(SAMPLE_CONSUMER_KEY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForEmptyConsumerSecret() throws BearerTokenCreationException {
        twitterKeyUtil.createEncodedBearerToken(SAMPLE_CONSUMER_KEY, "");
    }

}
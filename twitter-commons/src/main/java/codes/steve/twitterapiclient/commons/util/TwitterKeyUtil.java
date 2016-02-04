package codes.steve.twitterapiclient.commons.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.google.common.base.Strings.isNullOrEmpty;


public class TwitterKeyUtil {

    public String createEncodedBearerToken(String consumerKey, String consumerSecret) throws BearerTokenCreationException {
        if (isNullOrEmpty(consumerKey)) {
            throw new IllegalArgumentException("ConsumerKey is not set");
        }

        if (isNullOrEmpty(consumerSecret)) {
            throw new IllegalArgumentException("ConsumerSecret is not set");
        }

        // 1. Encode the keys using RFC 1738
        String encodedConsumerKey = encodeKey(consumerKey);
        String encodedConsumerSecret = encodeKey(consumerSecret);

        // 2. Concatenate using a ':'
        String bearerTokenCredentials = createCredentials(encodedConsumerKey, encodedConsumerSecret);

        // 3. Base 64 encode
        return base64Encode(bearerTokenCredentials);
    }

    private String encodeKey(String key) throws BearerTokenCreationException {
        try {
            return URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BearerTokenCreationException("Failed to encode key", e);
        }
    }

    private String createCredentials(String encodedConsumerKey, String encodedConsumerSecret) {
        return encodedConsumerKey + ":" + encodedConsumerSecret;
    }

    private String base64Encode(String bearerTokenCredentials) {
        byte[] encodedBearerCredentials = Base64.encodeBase64(bearerTokenCredentials.getBytes());
        return new String(encodedBearerCredentials);
    }
}

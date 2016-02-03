package me.sbio.twitterstub.mappings;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.google.common.io.ByteStreams;

import java.io.IOException;

public class FileBasedResponse extends ResponseDefinitionBuilder {

    public FileBasedResponse(String filename) {
        withBody(fromFile(filename));
    }

    protected byte[] fromFile(String filename) {
        try {
            return ByteStreams.toByteArray(this.getClass().getResourceAsStream("/" + filename));
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to find file " + filename);
        }
    }
}

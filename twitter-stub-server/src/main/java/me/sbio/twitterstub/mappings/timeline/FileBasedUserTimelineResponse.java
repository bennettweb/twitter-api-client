package me.sbio.twitterstub.mappings.timeline;

import me.sbio.twitterstub.mappings.FileBasedResponse;

class FileBasedUserTimelineResponse extends FileBasedResponse {

    public FileBasedUserTimelineResponse(String filename) {
        super(filename);
        withStatus(200);
        withHeader("Content-Type", "application/json; charset=UTF-8");
    }

}

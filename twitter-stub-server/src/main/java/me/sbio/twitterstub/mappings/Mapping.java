package me.sbio.twitterstub.mappings;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

public interface Mapping {
    MappingBuilder request();

    ResponseDefinitionBuilder response();
}

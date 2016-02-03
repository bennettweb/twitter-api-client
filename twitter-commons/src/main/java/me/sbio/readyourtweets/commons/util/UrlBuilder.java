package me.sbio.readyourtweets.commons.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;

public class UrlBuilder {

    private String baseUrl;
    private LinkedList<String> segments;
    private Map<String, String> parameters;
    private int port;

    public UrlBuilder(String baseUrl) {
        this.segments = Lists.newLinkedList();
        this.parameters = Maps.newHashMap();
        this.baseUrl = baseUrl;
        this.port = -1;
    }

    public UrlBuilder withPathSegment(String segment) {
        this.segments.add(segment);
        return this;
    }

    public UrlBuilder withParameter(String name, String value) {
        parameters.put(name, value);
        return this;
    }

    public String build() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(baseUrl);

        if (port > 0) {
            buffer.append(":").append(port);
        }

        if (!buffer.toString().endsWith("/") && segments.size() > 0) {
            buffer.append("/");
        }

        String path = path(segments);
        if (!path.equals("/")) {
            buffer.append(path);
        }
        buffer.append(queryString(parameters));

        return buffer.toString();
    }

    private String queryString(Map<String, String> parameters) {
        if (parameters.size() == 0) {
            return "";
        }

        return "?" + Joiner.on("&").withKeyValueSeparator("=").join(parameters.entrySet());
    }

    private String path(LinkedList<String> segments) {
        return Joiner.on("/").skipNulls().join(segments).replaceAll("//", "/");
    }

    public UrlBuilder withPort(int port) {
        this.port = port;
        return this;
    }
}

package me.sbio.readyourtweets.commons.util;

import me.sbio.readyourtweets.commons.util.UrlBuilder;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class UrlBuilderTest {

    private static final String BASE_URL = "http://localhost";

    @Test
    public void shouldCreateAUrlWithoutSegments() {
        String url = new UrlBuilder("http://localhost/").build();
        assertThat(url).isEqualTo("http://localhost/");
    }

    @Test
    public void shouldCreateAUrlWithoutSegmentsAddingTrailingSlash() {
        String url = new UrlBuilder(BASE_URL).build();
        assertThat(url).isEqualTo("http://localhost");
    }

    @Test
    public void shouldCreateAUrlWithSingleSegment() {
        String url = new UrlBuilder(BASE_URL).withPathSegment("hello").build();
        assertThat(url).isEqualTo("http://localhost/hello");
    }

    @Test
    public void shouldCreateAUrlWithMultipleSegments() {
        String url = new UrlBuilder(BASE_URL).
                withPathSegment("hello").
                withPathSegment("cruel").
                withPathSegment("world").build();

        assertThat(url).isEqualTo("http://localhost/hello/cruel/world");
    }

    @Test
    public void shouldCreateAUrlWithoutRepeatingSlashes() {
        String url = new UrlBuilder(BASE_URL).
                withPathSegment("hello/").
                withPathSegment("cruel").
                withPathSegment("/world").build();

        assertThat(url).isEqualTo("http://localhost/hello/cruel/world");
    }

    @Test
    public void shouldCreateAUrlWithParameters() {
        String url = new UrlBuilder(BASE_URL).
                withPathSegment("hello").
                withParameter("name", "sbio").build();

        assertThat(url).isEqualTo("http://localhost/hello?name=sbio");
    }

    @Test
    public void shouldCreateAUrlWithSingleInstanceOfParametersWhenParameterSpecifiedMultipleTimes() {
        String url = new UrlBuilder(BASE_URL).
                withPathSegment("hello").
                withParameter("name", "twitter").
                withParameter("name", "sbio").build();

        assertThat(url).isEqualTo("http://localhost/hello?name=sbio");
    }

    @Test
    public void shouldCreateAUrlWithMultipleParameters() {
        String url = new UrlBuilder(BASE_URL).
                withPathSegment("hello").
                withParameter("count", "2").
                withParameter("name", "sbio").build();

        assertThat(url).isEqualTo("http://localhost/hello?count=2&name=sbio");
    }

    @Test
    public void shouldCreateAUrlAppendingPort() {
        String url = new UrlBuilder(BASE_URL).withPort(8888).build();

        assertThat(url).isEqualTo("http://localhost:8888");
    }

}
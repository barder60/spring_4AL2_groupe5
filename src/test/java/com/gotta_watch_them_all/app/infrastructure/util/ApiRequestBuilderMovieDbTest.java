package com.gotta_watch_them_all.app.infrastructure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.URI;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBootConfiguration
@TestPropertySource(properties = {
        "moviedb.api.key=key",
        "moviedb.api.host=host",
})
class ApiRequestBuilderMovieDbTest {

    @Value("${moviedb.api.key}")
    private String apiKey;

    @Value("${moviedb.api.host}")
    private String apiHost;

    private ApiRequestBuilderMovieDb sut;

    private HttpRequest requestModel;

    private final String apiUrl = "https://movie-database.test";

    @BeforeEach
    public void setup() {
        sut = new ApiRequestBuilderMovieDb();
        ReflectionTestUtils.setField(sut, "apiKey", apiKey);
        ReflectionTestUtils.setField(sut, "apiHost", apiHost);

        requestModel = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", apiHost)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    @Test
    public void setUri_should_set_uri_with_given_url() {
        sut.setUri(apiUrl);
        Object uri = ReflectionTestUtils.getField(sut, "uri");
        assertEquals(URI.create(apiUrl), uri);
    }

    @Test
    public void setUri_should_throw_null_pointer_exception_when_given_url_is_null() {
        assertThrows(NullPointerException.class, () -> sut.setUri(null));
    }

    @Test
    public void build_should_return_http_request_with_right_uri() {
        HttpRequest requestFromBuilder = sut
                .setUri(apiUrl)
                .build();

        assertEquals(requestModel.uri(), requestFromBuilder.uri());
    }

    @Test
    public void build_should_return_http_request_with_right_headers() {
        HttpRequest requestFromBuilder = sut
                .setUri(apiUrl)
                .build();

        assertEquals(requestModel.headers(), requestFromBuilder.headers());
    }

    @Test
    public void build_should_return_http_request_with_right_method() {
        HttpRequest requestFromBuilder = sut
                .setUri(apiUrl)
                .build();

        assertEquals(requestModel.method(), requestFromBuilder.method());
    }
}

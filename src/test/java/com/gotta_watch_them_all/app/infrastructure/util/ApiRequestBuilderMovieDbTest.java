package com.gotta_watch_them_all.app.infrastructure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;

class ApiRequestBuilderMovieDbTest {

    private ApiRequestBuilderMovieDb sut;

    @BeforeEach
    public void setup() {
        sut = new ApiRequestBuilderMovieDb();
    }

    @Test
    public void should_return_http_request() {
        String url = "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", "6da17873e5msh1a5598a15b875ddp151843jsnfe48d4eaed87")
                .header("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        assertEquals(request, sut.build(url));
    }
}

package com.gotta_watch_them_all.app.infrastructure.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;

@Service
public class ApiRequestBuilderMovieDb implements ApiRequestBuilder {

    private URI uri;

    @Value("${moviedb.api.key}")
    private String apiKey;

    @Value("${moviedb.api.host}")
    private String apiHost;

    @Override
    public HttpRequest build() {
        return HttpRequest
                .newBuilder()
                .uri(uri)
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", apiHost)
                .build();
    }

    @Override
    public ApiRequestBuilderMovieDb setUri(String url) {
        uri = URI.create(url);
        return this;
    }
}

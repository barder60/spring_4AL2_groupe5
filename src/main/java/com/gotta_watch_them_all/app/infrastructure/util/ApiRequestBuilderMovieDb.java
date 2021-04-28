package com.gotta_watch_them_all.app.infrastructure.util;

import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
public class ApiRequestBuilderMovieDb implements ApiRequestBuilder {

    @Override
    public HttpRequest build(String url) {
        return null;
    }
}

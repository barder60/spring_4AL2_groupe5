package com.gotta_watch_them_all.app.infrastructure.util;

import java.net.http.HttpRequest;

public interface ApiRequestBuilder {
    HttpRequest build(String url);
}

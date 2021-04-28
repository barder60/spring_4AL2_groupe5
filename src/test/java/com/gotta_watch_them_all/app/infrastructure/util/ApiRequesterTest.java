package com.gotta_watch_them_all.app.infrastructure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;

class ApiRequesterTest {

    private ApiRequester sut;

    @Mock
    HttpClient httpClientMock;

    @Mock
    ApiRequestBuilder apiRequestBuilderMock;

    @BeforeEach
    public void setup() {
        sut = new ApiRequester(httpClientMock);
    }

    @Test
    public void should_return_api_data_in_string() {
        String url = "url";
        String expextedData = "{\n" +
                "\"Title\":\"Avengers: Endgame\"\n" +
                "\"Year\":\"2019\"\n" +
                "}";
        //mockito when etc
        HttpRequest request = apiRequestBuilderMock.build(url);

        assertEquals(expextedData, sut.request(request));
    }

    //should call http client once
    //etc...

}

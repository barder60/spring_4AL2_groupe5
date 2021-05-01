package com.gotta_watch_them_all.app.infrastructure.dao;

import com.gotta_watch_them_all.app.core.entity.Work;
import com.gotta_watch_them_all.app.core.exception.AnySearchValueFoundException;
import com.gotta_watch_them_all.app.core.exception.BadHttpRequestException;
import com.gotta_watch_them_all.app.core.exception.IllegalTitleGivenException;
import com.gotta_watch_them_all.app.infrastructure.util.ApiRequestBuilder;
import com.gotta_watch_them_all.app.infrastructure.util.ApiRequester;
import com.gotta_watch_them_all.app.infrastructure.util.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkDaoMovieDbApiTest {

    private WorkDaoMovieDbApi sut;

    private ApiRequester apiRequesterMock;
    private ApiRequestBuilder apiRequestBuilderMock;
    private JsonParser jsonParserMock;

    @BeforeEach
    public void setup() {
        apiRequesterMock = Mockito.mock(ApiRequester.class);
        apiRequestBuilderMock = Mockito.mock(ApiRequestBuilder.class);
        jsonParserMock = Mockito.mock(JsonParser.class);
        sut = new WorkDaoMovieDbApi(apiRequestBuilderMock, apiRequesterMock, jsonParserMock);
    }

    @Test
    public void findAllByTitle_should_call_api_req_builder_once() throws AnySearchValueFoundException, IllegalTitleGivenException {
        Mockito.when(apiRequestBuilderMock.setTitleToSearch(Mockito.anyString())).thenReturn(apiRequestBuilderMock);
        sut.findAllByTitle("title");
        Mockito.verify(apiRequestBuilderMock, Mockito.times(1)).build();
    }

    @Test
    public void findAllByTitle_should_call_api_request_builder_set_search_title() throws IllegalTitleGivenException {
        Mockito.when(apiRequestBuilderMock.setTitleToSearch(Mockito.anyString())).thenReturn(apiRequestBuilderMock);
        sut.findAllByTitle("bonjour");
        Mockito.verify(apiRequestBuilderMock, Mockito.times(1)).setTitleToSearch("bonjour");
    }

    @Test
    public void findAllByTitle_should_call_api_req_once() throws BadHttpRequestException, IllegalTitleGivenException {
        Mockito.when(apiRequestBuilderMock.setTitleToSearch(Mockito.anyString())).thenReturn(apiRequestBuilderMock);
        sut.findAllByTitle("title");
        Mockito.verify(apiRequesterMock, Mockito.times(1)).request(Mockito.any());
    }

    @Test
    public void findAllByTitle_should_call_json_parser_once_with_work_class() throws IllegalTitleGivenException, BadHttpRequestException {
        Mockito.when(apiRequestBuilderMock.setTitleToSearch(Mockito.anyString())).thenReturn(apiRequestBuilderMock);
        Mockito.when(apiRequesterMock.request(Mockito.any())).thenReturn("response");
        sut.findAllByTitle("title");
        Mockito.verify(jsonParserMock, Mockito.times(1)).toObjectList(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void findAllByTitle_should_return_all_works_containing_title() throws IllegalTitleGivenException, AnySearchValueFoundException, BadHttpRequestException {
        List<Object> expectedWorksList = new ArrayList<>();
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et cela"));

        Set<Work> expectedWorksSet = new HashSet<>();
        expectedWorksSet.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksSet.add(new Work().setId("1").setTitle("Harry Potter et cela"));

        Mockito.when(apiRequestBuilderMock.setTitleToSearch(Mockito.anyString())).thenReturn(apiRequestBuilderMock);
        Mockito.when(apiRequestBuilderMock.build()).thenReturn(HttpRequest.newBuilder().uri(URI.create("https://local")).build());
        Mockito.when(apiRequesterMock.request(Mockito.any())).thenReturn("response");
        Mockito.when(jsonParserMock.toObjectList(Mockito.any(), Mockito.any()))
                .thenReturn(expectedWorksList);

        assertEquals(expectedWorksSet, sut.findAllByTitle("titre"));
    }

    @Test
    public void findAllByTitle_should_return_all_works_containing_title_without_duplicates() throws IllegalTitleGivenException, AnySearchValueFoundException, BadHttpRequestException {
        List<Object> expectedWorksList = new ArrayList<>();
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et cela"));
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et cela"));

        Set<Work> expectedWorksSet = new HashSet<>();
        expectedWorksSet.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksSet.add(new Work().setId("1").setTitle("Harry Potter et cela"));

        Mockito.when(apiRequestBuilderMock.setTitleToSearch(Mockito.anyString())).thenReturn(apiRequestBuilderMock);
        Mockito.when(apiRequestBuilderMock.build()).thenReturn(HttpRequest.newBuilder().uri(URI.create("https://local")).build());
        Mockito.when(apiRequesterMock.request(Mockito.any())).thenReturn("response");
        Mockito.when(jsonParserMock.toObjectList(Mockito.any(), Mockito.any()))
                .thenReturn(expectedWorksList);

        assertEquals(expectedWorksSet, sut.findAllByTitle("titre"));
    }

    @Test
    public void findAllByTitle_should_throw_exception_if_json_malformed() throws IllegalTitleGivenException, AnySearchValueFoundException, BadHttpRequestException {
        List<Object> expectedWorksList = new ArrayList<>();
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et cela"));
        expectedWorksList.add(new Work().setId("1").setTitle("Harry Potter et cela"));

        Set<Work> expectedWorksSet = new HashSet<>();
        expectedWorksSet.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorksSet.add(new Work().setId("1").setTitle("Harry Potter et cela"));

        Mockito.when(apiRequestBuilderMock.setTitleToSearch(Mockito.anyString())).thenReturn(apiRequestBuilderMock);
        Mockito.when(apiRequestBuilderMock.build()).thenReturn(HttpRequest.newBuilder().uri(URI.create("https://local")).build());
        Mockito.when(apiRequesterMock.request(Mockito.any())).thenReturn("response");
        Mockito.when(jsonParserMock.toObjectList(Mockito.any(), Mockito.any()))
                .thenReturn(expectedWorksList);

        assertEquals(expectedWorksSet, sut.findAllByTitle("titre"));
    }


}

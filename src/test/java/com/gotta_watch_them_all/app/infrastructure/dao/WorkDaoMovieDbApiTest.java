package com.gotta_watch_them_all.app.infrastructure.dao;

import com.gotta_watch_them_all.app.core.entity.Work;
import com.gotta_watch_them_all.app.infrastructure.util.ApiRequestBuilder;
import com.gotta_watch_them_all.app.infrastructure.util.ApiRequester;
import com.gotta_watch_them_all.app.infrastructure.util.WorkParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WorkDaoMovieDbApiTest {

    private WorkDaoMovieDbApi sut;

    @Mock
    private ApiRequester apiRequester;

    @Mock
    private ApiRequestBuilder apiRequestBuilder;

    @Mock
    private WorkParser workParser;

    @BeforeEach
    public void setup() {
        sut = new WorkDaoMovieDbApi(apiRequestBuilder, apiRequester, workParser);
    }

    @Test
    public void find_by_name_should_return_all_works_containing_name() {
        String title = "Harry Potter";
        Set<Work> expectedWorks = new HashSet<>();
        expectedWorks.add(new Work().setId(1L).setTitle("Harry Potter et ceci"));
        expectedWorks.add(new Work().setId(1L).setTitle("Harry Potter et cela"));

        assertEquals(expectedWorks, sut.findAllByTitle(title));
    }

}

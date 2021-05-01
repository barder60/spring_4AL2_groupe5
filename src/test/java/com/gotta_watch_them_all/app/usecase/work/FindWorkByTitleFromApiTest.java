package com.gotta_watch_them_all.app.usecase.work;

import com.gotta_watch_them_all.app.core.entity.Work;
import com.gotta_watch_them_all.app.infrastructure.dao.WorkDaoMovieDbApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindWorkByTitleFromApiTest {

    private FindWorkByTitleFromApi sut;

    private WorkDaoMovieDbApi workDaoMovieDbApiMock;

    @BeforeEach
    public void setup() {
        workDaoMovieDbApiMock = Mockito.mock(WorkDaoMovieDbApi.class);
        sut = new FindWorkByTitleFromApi(workDaoMovieDbApiMock);
    }

    @Test
    public void execute_should_call_dao_once_with_same_title() {
        sut.execute("titre");
        Mockito.verify(workDaoMovieDbApiMock, Mockito.times(1)).findAllByTitle("titre");
    }

    @Test
    public void should_parse_works_from_api() {
        Set<Work> expectedWorks = new HashSet<>();
        expectedWorks.add(new Work().setId("1").setTitle("Harry Potter et ceci"));
        expectedWorks.add(new Work().setId("1").setTitle("Harry Potter et cela"));

        Mockito.when(workDaoMovieDbApiMock.findAllByTitle(Mockito.anyString())).thenReturn(expectedWorks);

        assertEquals(expectedWorks, sut.execute("title"));
    }

    @Test
    public void should_return_empty_set_if_title_null() {
        assertEquals(new HashSet<>(), sut.execute(null));
    }

    @Test
    public void should_return_empty_set_if_title_empty() {
        assertEquals(new HashSet<>(), sut.execute(" "));
    }


}

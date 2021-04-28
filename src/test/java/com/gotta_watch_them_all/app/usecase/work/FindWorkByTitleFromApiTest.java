package com.gotta_watch_them_all.app.usecase.work;

import com.gotta_watch_them_all.app.core.entity.Work;
import com.gotta_watch_them_all.app.infrastructure.dao.WorkDaoMovieDbApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindWorkByTitleFromApiTest {

    private FindWorkByTitleFromApi sut;

    @Mock
    private WorkDaoMovieDbApi workDaoMovieDbApiMock;

    @BeforeEach
    public void setup() {
        sut = new FindWorkByTitleFromApi(workDaoMovieDbApiMock);
    }

    @Test
    public void should_parse_works_from_api() {
        String title = "Harry Potter";
        Set<Work> expectedWorks = new HashSet<>();
        expectedWorks.add(new Work().setId(1L).setTitle("Harry Potter et ceci"));
        expectedWorks.add(new Work().setId(1L).setTitle("Harry Potter et cela"));

        assertEquals(expectedWorks, sut.execute(title));
    }


}

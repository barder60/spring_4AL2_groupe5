package com.gotta_watch_them_all.app.infrastructure.util;

import com.gotta_watch_them_all.app.core.entity.Work;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WorkParserMovieDbApiTest {

    private WorkParserMovieDbApi sut;

    @BeforeEach
    public void setup() {
        sut = new WorkParserMovieDbApi();
    }

    @Test
    public void should_parse_json_into_set_of_works() {
        String json = "{   \n" +
                "    [{\"id\": 1,\n" +
                "    \"title\": \"Harry Potter et ceci\"},\n" +
                "    {\"id\": 2,\n" +
                "    \"title\": \"Harry Potter et cela\"}]\n" +
                "}";

        Set<Work> expectedWorks = new HashSet<>();
        expectedWorks.add(new Work().setId(1L).setTitle("Harry Potter et ceci"));
        expectedWorks.add(new Work().setId(1L).setTitle("Harry Potter et cela"));

        assertEquals(expectedWorks, sut.fromJsonToWorks(json));
    }

}

package com.gotta_watch_them_all.app.infrastructure.util;

import com.gotta_watch_them_all.app.core.entity.Work;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WorkParserMovieDbApi implements WorkParser {

    @Override
    public Set<Work> fromJsonToWorks(String json) {
        return null;
    }
}

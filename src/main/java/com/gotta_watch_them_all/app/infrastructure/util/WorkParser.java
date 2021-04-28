package com.gotta_watch_them_all.app.infrastructure.util;

import com.gotta_watch_them_all.app.core.entity.Work;

import java.util.Set;

public interface WorkParser {
    Set<Work> fromJsonToWorks(String json);
}

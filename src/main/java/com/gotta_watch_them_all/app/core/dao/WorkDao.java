package com.gotta_watch_them_all.app.core.dao;

import com.gotta_watch_them_all.app.core.entity.Work;

import java.util.Set;

public interface WorkDao {
    Set<Work> findAllByTitle(String title);
}

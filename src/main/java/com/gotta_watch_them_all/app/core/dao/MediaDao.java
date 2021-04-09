package com.gotta_watch_them_all.app.core.dao;

import com.gotta_watch_them_all.app.core.entity.Media;

import java.util.List;

public interface MediaDao {
    List<Media> findAll();

    Media findById(Long mediaId);
}

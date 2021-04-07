package com.gotta_watch_them_all.app.infrastructure.dao;

import com.gotta_watch_them_all.app.core.dao.MediaDao;
import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaDaoImpl implements MediaDao {
    private final MediaRepository mediaRepository;

    @Override
    public List<Media> findAll() {
        return null;
    }
}

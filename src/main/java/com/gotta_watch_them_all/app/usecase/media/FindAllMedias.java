package com.gotta_watch_them_all.app.usecase.media;

import com.gotta_watch_them_all.app.core.dao.MediaDao;
import com.gotta_watch_them_all.app.core.entity.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllMedias {
    private final MediaDao mediaDao;

    public List<Media> execute() {
        return null;
    }
}

package com.gotta_watch_them_all.app.usecase.media;

import com.gotta_watch_them_all.app.core.dao.MediaDao;
import com.gotta_watch_them_all.app.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMedia {
    private final MediaDao mediaDao;

    public void execute(Long mediaId) throws NotFoundException {
        checkIfMediaFoundByIdOrThrowException(mediaId);

        mediaDao.deleteMedia(mediaId);
    }

    private void checkIfMediaFoundByIdOrThrowException(Long mediaId) throws NotFoundException {
        if (!mediaDao.existsById(mediaId)) {
            var message = String.format("Media with id '%d' not found", mediaId);
            throw new NotFoundException(message);
        }
    }
}

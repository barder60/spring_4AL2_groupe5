package com.gotta_watch_them_all.app.infrastructure.entrypoint.adapter;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.response.MediaResponse;

public class MediaAdapter {
    public static MediaResponse domainToResponse(Media media) {
        return new MediaResponse()
                .setId(media.getId())
                .setName(media.getName());
    }
}

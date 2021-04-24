package com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;

public class MediaMapper {
    public static Media entityToDomain(MediaEntity entity) {
        return new Media()
                .setId(entity.getId())
                .setName(entity.getName());
    }

    public static MediaEntity domainToEntity(Media domain) {
        return new MediaEntity()
                .setId(domain.getId())
                .setName(domain.getName());
    }
}

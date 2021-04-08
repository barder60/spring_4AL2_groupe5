package com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.mapper;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.MediaEntity;

public class MediaMapper {
    public static MediaEntity domainToEntity(Media domain) {
        return MediaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }
    public static Media entityToDomain(MediaEntity entity) {
        return Media.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}

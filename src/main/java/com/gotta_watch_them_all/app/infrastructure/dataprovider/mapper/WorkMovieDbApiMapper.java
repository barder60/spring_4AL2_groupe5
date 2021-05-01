package com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper;

import com.gotta_watch_them_all.app.core.entity.Media;
import com.gotta_watch_them_all.app.core.entity.Work;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.WorkMovieDbApiEntity;

public class WorkMovieDbApiMapper {
    public Work toDomain(WorkMovieDbApiEntity entity) {
        if (entity == null) return new Work();
        return new Work()
                .setId(entity.getImdbID())
                .setTitle(entity.getTitle())
                .setYear(entity.getYear())
                .setMedia(mapMedia(entity.getType()));
    }

    public WorkMovieDbApiEntity toEntity(Work work) {
        return null;
    }

    private Media mapMedia(String type) {
        if (type.equalsIgnoreCase("movie")) {
            return new Media().setName("Film");
        } else if (type.equalsIgnoreCase("series")) {
            return new Media().setName("Série");
        } else if (type.equalsIgnoreCase("episode")) {
            return new Media().setName("Episode");
        }

        return new Media();
    }


}

package com.gotta_watch_them_all.app.infrastructure.dataprovider.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WorkMovieDbApiEntity {
    private String title;
    private Integer year;
    private String type;
    private String imdbID;
}

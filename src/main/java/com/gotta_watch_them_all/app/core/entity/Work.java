package com.gotta_watch_them_all.app.core.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class Work {
    private Long id;
    private String title;
    private String description;
    private String author;
    private Media media;
}

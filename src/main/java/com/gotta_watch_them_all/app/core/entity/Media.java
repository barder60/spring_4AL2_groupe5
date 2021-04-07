package com.gotta_watch_them_all.app.core.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Media {
    private Long id;
    private String name;
}

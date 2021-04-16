package com.gotta_watch_them_all.app.infrastructure.entrypoint.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonDeserialize
@Accessors(chain = true)
public class MediaResponse {
    private Long id;
    private String name;
}

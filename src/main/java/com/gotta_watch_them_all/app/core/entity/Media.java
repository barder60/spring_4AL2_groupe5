package com.gotta_watch_them_all.app.core.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Media.MediaBuilder.class)
@Builder(builderClassName = "MediaBuilder", toBuilder = true)
public class Media {
    private Long id;
    private String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MediaBuilder {

    }
}

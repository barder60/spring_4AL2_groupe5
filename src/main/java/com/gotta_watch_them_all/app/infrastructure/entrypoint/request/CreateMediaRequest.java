package com.gotta_watch_them_all.app.infrastructure.entrypoint.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateMediaRequest {
    @NotBlank(message = "Name has to be not blank")
    private String name;
}

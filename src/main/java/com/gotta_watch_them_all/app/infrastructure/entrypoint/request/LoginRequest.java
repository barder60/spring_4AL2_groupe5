package com.gotta_watch_them_all.app.infrastructure.entrypoint.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

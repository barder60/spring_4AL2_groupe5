package com.gotta_watch_them_all.app.infrastructure.entrypoint.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Accessors(chain = true)
public class SignupRequest {
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    
    private Set<String> roles;
}

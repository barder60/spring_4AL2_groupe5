package com.gotta_watch_them_all.app.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean vulgar;
}

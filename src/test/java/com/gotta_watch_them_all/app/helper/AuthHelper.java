package com.gotta_watch_them_all.app.helper;

import com.gotta_watch_them_all.app.core.dao.UserDao;
import com.gotta_watch_them_all.app.core.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthHelper {
    private final UserDao userDao;

    private String createUserAndGetJwt(
            String username,
            String email,
            String password,
            Set<Role> roles
    ) {
        return null;
    }
}

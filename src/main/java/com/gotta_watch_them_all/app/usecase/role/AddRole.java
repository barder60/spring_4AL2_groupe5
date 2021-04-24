package com.gotta_watch_them_all.app.usecase.role;

import com.gotta_watch_them_all.app.core.dao.RoleDao;
import com.gotta_watch_them_all.app.core.entity.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddRole {
    private final RoleDao roleDao;

    public Long execute(RoleName roleName) {
        return roleDao.createRole(roleName);
    }
}

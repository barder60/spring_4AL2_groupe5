package com.gotta_watch_them_all.app.usecase.role;

import com.gotta_watch_them_all.app.core.dao.RoleDao;
import com.gotta_watch_them_all.app.core.entity.Role;
import com.gotta_watch_them_all.app.core.entity.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindRoleByRoleName {
    private final RoleDao roleDao;

    public Role execute(RoleName roleName) {
        return roleDao.findByRoleName(roleName);
    }
}

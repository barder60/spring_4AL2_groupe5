package com.gotta_watch_them_all.app.core.dao;

import com.gotta_watch_them_all.app.core.entity.Role;
import com.gotta_watch_them_all.app.core.entity.RoleName;

import java.util.List;

public interface RoleDao {
    Long createRole(RoleName roleName);

    Role findByRoleName(RoleName roleName);

    List<Role> findAll();
}

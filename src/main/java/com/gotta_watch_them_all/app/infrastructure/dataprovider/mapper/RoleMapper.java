package com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper;

import com.gotta_watch_them_all.app.core.entity.Role;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.RoleEntity;

public class RoleMapper {
    public static Role entityToDomain(RoleEntity entity) {
        return new Role()
                .setId(entity.getId())
                .setName(entity.getName());
    }

    public static RoleEntity domainToEntity(Role role) {
        return new RoleEntity()
                .setId(role.getId())
                .setName(role.getName());
    }
}

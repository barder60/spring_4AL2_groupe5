package com.gotta_watch_them_all.app.infrastructure.dao;

import com.gotta_watch_them_all.app.core.dao.RoleDao;
import com.gotta_watch_them_all.app.core.entity.RoleName;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.RoleEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private final RoleRepository roleRepository;

    @Override
    public Long createRole(RoleName roleName) {
        var savedRoleEntity = roleRepository.save(new RoleEntity().setName(roleName));
        return savedRoleEntity.getId();
    }
}

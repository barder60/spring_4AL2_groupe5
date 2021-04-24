package com.gotta_watch_them_all.app.infrastructure.dao;

import com.gotta_watch_them_all.app.core.dao.RoleDao;
import com.gotta_watch_them_all.app.core.entity.Role;
import com.gotta_watch_them_all.app.core.entity.RoleName;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.RoleEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper.RoleMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private final RoleRepository roleRepository;

    @Override
    public Long createRole(RoleName roleName) {
        var savedRoleEntity = roleRepository.save(new RoleEntity().setName(roleName));
        return savedRoleEntity.getId();
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByName(roleName)
                .map(RoleMapper::entityToDomain)
                .orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}

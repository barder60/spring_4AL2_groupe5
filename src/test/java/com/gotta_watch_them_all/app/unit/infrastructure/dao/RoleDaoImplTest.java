package com.gotta_watch_them_all.app.unit.infrastructure.dao;

import com.gotta_watch_them_all.app.core.entity.RoleName;
import com.gotta_watch_them_all.app.infrastructure.dao.RoleDaoImpl;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.RoleEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleDaoImplTest {

    @Mock
    private RoleRepository mockRoleRepository;

    private RoleDaoImpl sut;

    @BeforeEach
    void setUp() {
        sut = new RoleDaoImpl(mockRoleRepository);
    }

    @Test
    void createRole_shouldReturnIdOfSavedRole() {
        RoleName roleName = RoleName.ROLE_USER;
        RoleEntity roleEntity = new RoleEntity().setName(roleName);
        Long expectedId = 1L;

        when(mockRoleRepository.save(roleEntity))
                .thenReturn(new RoleEntity().setId(expectedId).setName(roleName));

        var result = sut.createRole(roleName);

        assertThat(result).isEqualTo(expectedId);
    }
}
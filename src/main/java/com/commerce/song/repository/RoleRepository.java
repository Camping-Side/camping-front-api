package com.commerce.song.repository;

import com.commerce.song.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleNm(String name);

    @Override
    void delete(Role role);
}

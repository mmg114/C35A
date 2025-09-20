package com.parquesoftti.tess.repository;

import com.parquesoftti.tess.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRespository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    Role findByDescription(String description);
}

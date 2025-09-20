package com.parquesoftti.tess.repository;

import com.parquesoftti.tess.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmailAndEnabled(String email, Boolean enabled);

}

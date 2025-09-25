package com.parquesoftti.tess.repository;

import com.parquesoftti.tess.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmailAndEnabled(String email, Boolean enabled);
    Optional<AppUser> findByEmail(String email);
}

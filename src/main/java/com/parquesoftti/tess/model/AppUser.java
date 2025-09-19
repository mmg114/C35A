package com.parquesoftti.tess.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "AppUsers")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String passwordHash;
    @Column
    private Boolean enabled;
    @Column
    private Boolean accountNonExpired ;
    @Column
    private Boolean credentialsNonExpired;
    @Column
    private Boolean accountNonLocked;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
}

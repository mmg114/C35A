package com.parquesoftti.tess.service;

import com.parquesoftti.tess.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
   List<AppUser> getAllUsers();
   Optional<AppUser> getAppUser(Long id);
   AppUser saveAppUser(AppUser appUser);
   void deleteAppUser(Long id);
}

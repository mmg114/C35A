package com.parquesoftti.tess.service;

import com.parquesoftti.tess.model.AppUser;
import com.parquesoftti.tess.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppUserServiceImpl implements AppUserService{


    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> getAppUser(Long id) {

        if(id == null || id == 0){
            return Optional.empty();
        }
        return appUserRepository.findById(id);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        return null;
    }

    @Override
    public void deleteAppUser(Long id) {

    }
}

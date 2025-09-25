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
        if(appUser == null ){
            return null;
        }

        if(appUser.getEmail() == null || appUser.getEmail().isEmpty() ){
            return null;
        }

        if(appUser.getPasswordHash() == null || appUser.getPasswordHash().isEmpty() ){
            return null;
        }

        if(appUser.getEnabled() == null  ){
            return null;
        }
        return appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUser(Long id) {
        if(id != null && id != 0){
           appUserRepository.deleteById(id);
        }
    }

    @Override
    public AppUser updateAppUser(AppUser appUser,Long id) {

        if(appUser == null || id == null ){
            return null;
        }

        AppUser usuario = getAppUser(id).orElse(null);

        if(usuario != null){
            usuario.setUsername(appUser.getUsername());
            usuario.setPasswordHash(appUser.getPasswordHash());
            usuario.setEnabled(appUser.getEnabled());
            return saveAppUser(usuario);
        }else{
            return null;
        }

    }

    @Override
    public Optional<AppUser> findByEmailAndEnabled(String email, Boolean enabled) {
        return appUserRepository.findByEmailAndEnabled(email,enabled);
    }
}

package com.parquesoftti.tess.controller;


import com.parquesoftti.tess.model.AppUser;
import com.parquesoftti.tess.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping
    List<AppUser> getAllUsers(){
        return  appUserService.getAllUsers();
    }


    @GetMapping("/get-user/{id}")
    Optional<AppUser> getAppUser( @PathVariable Long id){
        return  appUserService.getAppUser( id);
    }







}

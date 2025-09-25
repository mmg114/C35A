package com.parquesoftti.tess.controller;


import com.parquesoftti.tess.model.AppUser;
import com.parquesoftti.tess.service.AppUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "User", description = "User Controller")
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


    @GetMapping("/{id}")
    Optional<AppUser> getAppUser( @PathVariable Long id){
        return  appUserService.getAppUser( id);
    }

    @PostMapping("")
    AppUser saveAppUser(@RequestBody AppUser appUser){
        return appUserService.saveAppUser(appUser);
    }

    @PutMapping("/{id}")
    AppUser updateAppUser(@RequestBody AppUser appUser, @PathVariable Long id){
        return appUserService.updateAppUser(appUser,id);
    }

    @DeleteMapping("/{id}")
    void deleteAppUser(@PathVariable Long id){
        appUserService.deleteAppUser(id);
    }

    @GetMapping("/find-by-email-and-enabled")
    Optional<AppUser> findByEmailAndEnabled( @RequestParam String email, @RequestParam Boolean enabled){
        return  appUserService.findByEmailAndEnabled(email,enabled);
    }




}

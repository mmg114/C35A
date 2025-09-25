package com.parquesoftti.tess.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Roles", description = "Roles Controller")
@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @GetMapping()
    public String getAllRoles(){
        return "All roles";
    }
}

/*
package com.parquesoftti.tess;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.parquesoftti.tess.controller.AppUserController;
import com.parquesoftti.tess.model.AppUser;
import com.parquesoftti.tess.service.AppUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppUserController.class)
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserService;

    @Autowired
    private ObjectMapper objectMapper;

    // --- Utilidad para crear usuarios de prueba ---
    private AppUser user(Long id, String password, String email, boolean enabled) {
        AppUser u = new AppUser();
        u.setId(id);
        u.setPasswordHash(password);
        u.setEmail(email);
        u.setEnabled(enabled);
        return u;
    }

    @Test
    @DisplayName("GET /api/users -> 200 OK con lista de usuarios")
    void getAllUsers_ok() throws Exception {
        List<AppUser> users = List.of(
                user(1L, "Alice", "alice@test.com", true),
                user(2L, "Bob", "bob@test.com", false)
        );
        given(appUserService.getAllUsers()).willReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Alice")))
                .andExpect(jsonPath("$[0].email", is("alice@test.com")))
                .andExpect(jsonPath("$[0].enabled", is(true)));
    }

    @Nested
    class GetByIdTests {
        @Test
        @DisplayName("GET /api/users/{id} -> 200 OK cuando existe")
        void getById_found() throws Exception {
            given(appUserService.getAppUser(1L)).willReturn(Optional.of(user(1L, "Alice", "alice@test.com", true)));

            mockMvc.perform(get("/api/users/{id}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is("Alice")))
                    .andExpect(jsonPath("$.email", is("alice@test.com")))
                    .andExpect(jsonPath("$.enabled", is(true)));
        }

        @Test
        @DisplayName("GET /api/users/{id} -> 404 Not Found cuando no existe")
        void getById_notFound() throws Exception {
            given(appUserService.getAppUser(999L)).willReturn(Optional.empty());

            // Spring MVC trata Optional vacío como 404
            mockMvc.perform(get("/api/users/{id}", 999L))
                    .andExpect(status().isNotFound());
        }
    }

    @Test
    @DisplayName("POST /api/users -> 200 OK y retorna el usuario creado")
    void saveUser_ok() throws Exception {
        AppUser payload = user(null, "Charlie", "charlie@test.com", true);
        AppUser saved = user(10L, "Charlie", "charlie@test.com", true);

        given(appUserService.saveAppUser(ArgumentMatchers.any(AppUser.class)))
                .willReturn(saved);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk()) // Tu controlador devuelve 200 (no 201)
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("Charlie")))
                .andExpect(jsonPath("$.email", is("charlie@test.com")))
                .andExpect(jsonPath("$.enabled", is(true)));
    }

    @Test
    @DisplayName("PUT /api/users/{id} -> 200 OK y retorna el usuario actualizado")
    void updateUser_ok() throws Exception {
        AppUser payload = user(null, "Alice Updated", "alice@test.com", false);
        AppUser updated = user(1L, "Alice Updated", "alice@test.com", false);

        given(appUserService.updateAppUser(ArgumentMatchers.any(AppUser.class), ArgumentMatchers.eq(1L)))
                .willReturn(updated);

        mockMvc.perform(put("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Alice Updated")))
                .andExpect(jsonPath("$.enabled", is(false)));
    }

    @Test
    @DisplayName("DELETE /api/users/{id} -> 200 OK sin cuerpo")
    void deleteUser_ok() throws Exception {
        doNothing().when(appUserService).deleteAppUser(5L);

        mockMvc.perform(delete("/api/users/{id}", 5L))
                .andExpect(status().isOk());

        verify(appUserService).deleteAppUser(5L);
    }

    @Nested
    class FindByEmailAndEnabled {
        @Test
        @DisplayName("GET /api/users/find-by-email-and-enabled -> 200 OK cuando existe")
        void find_ok() throws Exception {
            given(appUserService.findByEmailAndEnabled("alice@test.com", true))
                    .willReturn(Optional.of(user(1L, "Alice", "alice@test.com", true)));

            mockMvc.perform(get("/api/users/find-by-email-and-enabled")
                            .param("email", "alice@test.com")
                            .param("enabled", "true"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.email", is("alice@test.com")))
                    .andExpect(jsonPath("$.enabled", is(true)));
        }

        @Test
        @DisplayName("GET /api/users/find-by-email-and-enabled -> 404 Not Found cuando no existe")
        void find_notFound() throws Exception {
            given(appUserService.findByEmailAndEnabled("no@existe.com", false))
                    .willReturn(Optional.empty());

            mockMvc.perform(get("/api/users/find-by-email-and-enabled")
                            .param("email", "no@existe.com")
                            .param("enabled", "false"))
                    .andExpect(status().isNotFound());
        }
    }
}
*/

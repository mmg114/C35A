package com.parquesoftti.tess.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter {

    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){





    }

}

package com.slusarz.sandbox.headers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class HeaderHolderFilter extends OncePerRequestFilter {

    @Autowired
    private HeaderHolder headerHolder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = Optional.ofNullable(request.getHeader("X-TAG")).orElse("none");
            headerHolder.set(Map.of("X-TAG", header));
            filterChain.doFilter(request, response);
        } finally {
            headerHolder.set(new HashMap<>());
        }
    }
}

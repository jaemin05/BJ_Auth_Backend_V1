package com.example.authorizationserver.global.security;

import com.example.authorizationserver.global.error.exception.ExceptionFilter;
import com.example.authorizationserver.global.security.jwt.JwtTokenFilter;
import com.example.authorizationserver.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity security) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        ExceptionFilter exceptionFilter = new ExceptionFilter();

        security.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        security.addFilterBefore(exceptionFilter, JwtTokenFilter.class);
    }
}

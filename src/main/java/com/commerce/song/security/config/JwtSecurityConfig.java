package com.commerce.song.security.config;

import com.commerce.song.security.adapter.JwtAdapter;
import com.commerce.song.security.common.JwtAuthenticationEntryPoint;
import com.commerce.song.security.handler.JwtAccessDeniedHandler;
import com.commerce.song.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@Order(0)
@Slf4j
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(jwtAccessDeniedHandler)
        .and()
                .headers()
                .frameOptions()
                .sameOrigin()
        .and()  // 세션 안씀
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .authorizeRequests()
                .antMatchers("/api/v1/authenticate").permitAll()
                .antMatchers("/api/v1/account/test").hasRole("USER")
                .anyRequest()
                .authenticated()
        .and()
                .apply(new JwtAdapter(jwtTokenProvider));

    }
}

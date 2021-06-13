package com.commerce.song.security.config;

import com.commerce.song.security.adapter.JwtAdapter;
import com.commerce.song.security.common.JwtAuthenticationEntryPoint;
import com.commerce.song.security.factory.UrlResourcesMapFactoryBean;
import com.commerce.song.security.filter.PermitAllFilter;
import com.commerce.song.security.handler.JwtAccessDeniedHandler;
import com.commerce.song.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.commerce.song.security.provider.JwtTokenProvider;
import com.commerce.song.service.SecurityResourceService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
@Order(0)
@Slf4j
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final SecurityResourceService securityResourceService;
    private final String corsOrigin="http://localhost:3030";
    private String[] permitAllResources = {"/h2-console/**","/api/v1/test", "/api/v1/accounts/*","/api/v1/accounts/**"};

//    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> {
                            CorsConfiguration cors = new CorsConfiguration();
                            cors.setAllowedOrigins(
                                    Lists.newArrayList("*"));
                            cors.setAllowedMethods(Lists.newArrayList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            cors.setAllowedHeaders(Lists.newArrayList("*"));
                            return cors;
                        })
        .and()
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
                .anyRequest()
                .authenticated()
        .and()
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .apply(new JwtAdapter(jwtTokenProvider));

    }

    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {
        PermitAllFilter permitAllFilter = new PermitAllFilter(permitAllResources);
        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        permitAllFilter.setAccessDecisionManager(affirmativeBased());
        permitAllFilter.setAuthenticationManager(authenticationManagerBean());
        return permitAllFilter;
    }

    private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<? extends Object>> getAccessDecisionVoters() {
        return Arrays.asList(new RoleVoter());
    }

    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception {
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(), securityResourceService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {
        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setSecurityResourceService(securityResourceService);

        return urlResourcesMapFactoryBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

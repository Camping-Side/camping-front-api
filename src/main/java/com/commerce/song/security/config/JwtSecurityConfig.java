package com.commerce.song.security.config;

import com.commerce.song.security.adapter.JwtAdapter;
import com.commerce.song.security.common.JwtAuthenticationEntryPoint;
import com.commerce.song.security.factory.UrlResourcesMapFactoryBean;
import com.commerce.song.security.filter.JwtExceptionFilter;
import com.commerce.song.security.filter.JwtFilter;
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
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
@Order(0)
@Slf4j
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final SecurityResourceService securityResourceService;
    private final String corsOrigin="http://localhost:3030";
    private String[] permitAllResources = {"/h2-console/**", "/swagger-ui/**", "/swagger-resources/**" // swagger3
            , "/swagger-ui.html", "/webjars/springfox-swagger-ui/**" // swaggger2
            , "/api/v1/test/**", "/api/v1/test" , "/api/v1/admin/test/**", "/v2/api-docs"
            , "/api/v1/auth/login", "/api/v1/auth/sign", "/api/v1/auth/reissue"};

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
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        .and()
                .headers()
                .frameOptions()
                .sameOrigin()
        .and()  // 세션 안씀
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .apply(new JwtAdapter(jwtTokenProvider))
        .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()


        ;

    }

    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {
        PermitAllFilter permitAllFilter = new PermitAllFilter(permitAllResources);
        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource(securityResourceService));
        permitAllFilter.setAccessDecisionManager(affirmativeBased(securityResourceService));
        permitAllFilter.setAuthenticationManager(authenticationManagerBean());
        permitAllFilter.setRejectPublicInvocations(true);

        return permitAllFilter;
    }

    @Bean
    public AccessDecisionManager affirmativeBased(SecurityResourceService securityResourceService) {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters(securityResourceService));
        affirmativeBased.setAllowIfAllAbstainDecisions(false);
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<? extends Object>> getAccessDecisionVoters(SecurityResourceService securityResourceService) {
        AuthenticatedVoter authenticatedVoter = new AuthenticatedVoter();
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        RoleVoter roleVoter = new RoleVoter();
//        IpAddressVoter ipAddressVoter = new IpAddressVoter(securityResourceService);

        List<AccessDecisionVoter<? extends Object>> accessDecisionVoterList = Arrays.asList( authenticatedVoter, webExpressionVoter, roleVoter);
        return accessDecisionVoterList;

//        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
//        accessDecisionVoters.add(new RoleVoter());
//        return accessDecisionVoters;
    }

    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource(SecurityResourceService securityResourceService) throws Exception {
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean(securityResourceService).getObject(), securityResourceService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean(SecurityResourceService securityResourceService) {
        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setSecurityResourceService(securityResourceService);

        return urlResourcesMapFactoryBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

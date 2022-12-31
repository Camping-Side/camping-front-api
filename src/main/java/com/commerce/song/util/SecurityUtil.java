package com.commerce.song.util;

import com.commerce.song.exception.SecurityContextAvailbleException;
import com.commerce.song.security.domain.UserContextDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static String getCurrentEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new SecurityContextAvailbleException("Security Context 에 인증 정보가 없습니다.");
        }

        return authentication.getName();
    }
    public static Long getCurrentAccountId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null || !(authentication.getPrincipal() instanceof UserContextDto)) {
            throw  new SecurityContextAvailbleException("Security Context 에 인증 정보가 없습니다.");
        }

        UserContextDto principal = (UserContextDto) authentication.getPrincipal();

        return principal.getAccount().getId();
    }
}

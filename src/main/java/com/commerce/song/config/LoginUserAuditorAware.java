package com.commerce.song.config;

import com.camping.common.exception.SecurityContextAvailbleException;
import com.camping.common.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginUserAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Long accountId = null;
        try {
            accountId = SecurityUtil.getCurrentAccountId();
        } catch(SecurityContextAvailbleException e) {
            accountId = 0L;
        }
        return Optional.of(accountId);
    }
}

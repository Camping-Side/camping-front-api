package com.commerce.song.config;

import com.commerce.song.exception.SecurityContextAvailbleException;
import com.commerce.song.util.SecurityUtil;
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

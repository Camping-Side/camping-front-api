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
        Long memberId = null;
        try {
            memberId = SecurityUtil.getCurrentMemberId();
        } catch(SecurityContextAvailbleException e) {
            memberId = 0L;
        }
        return Optional.of(memberId);
    }
}

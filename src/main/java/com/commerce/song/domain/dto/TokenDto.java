package com.commerce.song.domain.dto;

import com.commerce.song.security.common.AccountContext;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;
    private String username;

    public TokenDto(AccountContext principal, String token) {
        this.token = token;
        this.username = principal.getUsername();
    }
}

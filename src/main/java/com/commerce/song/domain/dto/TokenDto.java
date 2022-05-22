package com.commerce.song.domain.dto;

import com.commerce.song.security.common.AccountContext;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String username;
    private Long accessTokenExpiresIn;

    @Data
    public static class TokenRequestDto {
        private String accessToken;
        private String refreshToken;
    }


}

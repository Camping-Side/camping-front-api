package com.commerce.song.domain.dto;

import com.commerce.song.security.common.AccountContext;
import lombok.*;

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


}

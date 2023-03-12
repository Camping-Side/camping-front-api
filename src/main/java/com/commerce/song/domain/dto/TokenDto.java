package com.commerce.song.domain.dto;

import com.commerce.song.security.common.AccountContext;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    @ApiModelProperty(value = "권한타입")
    private String grantType;
    @ApiModelProperty(value = "접근 토큰")
    private String accessToken;
    @ApiModelProperty(value = "재발급 토근")
    private String refreshToken;
    @ApiModelProperty(value = "유저명")
    private String username;
    @ApiModelProperty(value = "토큰만료시간", example = "0")
    private Long accessTokenExpiresIn;

    @Data
    public static class TokenRequestDto {
        @ApiModelProperty(value = "접근 토큰")
        private String accessToken;
        @ApiModelProperty(value = "재발급 토근")
        private String refreshToken;
    }


}

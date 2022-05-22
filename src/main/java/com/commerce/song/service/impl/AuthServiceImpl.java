package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.dto.TokenDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.RefreshToken;
import com.commerce.song.repository.AccountRepository;
import com.commerce.song.repository.RefreshTokenRepository;
import com.commerce.song.security.provider.JwtTokenProvider;
import com.commerce.song.service.AccountService;
import com.commerce.song.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResultDto<Long> signup(AccountDto reqDto) {
        if(accountRepository.existsByEmail(reqDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Account account = Account.builder()
                .username(reqDto.getUsername())
                .email(reqDto.getEmail())
                .password(passwordEncoder.encode(reqDto.getPassword()))
                .age(reqDto.getAge())
                .activated(true)
                .build();

        Account savedAccount = accountRepository.save(account);

        return ResultDto.res(HttpStatus.OK, savedAccount.getId());
    }

    @Transactional
    public ResultDto<TokenDto> login(AccountDto.LoginReq reqDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(reqDto.getEmail(), reqDto.getPassword());

        Authentication authenticate =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 인증정보를 기반으로 토큰정보 가져옴
        TokenDto tokenDto = jwtTokenProvider.createToken(authenticate);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authenticate.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return ResultDto.res(HttpStatus.OK, tokenDto);
    }

    // 토큰 재발급급
   @Transactional
    public ResultDto<TokenDto> reissue(TokenDto.TokenRequestDto reqDto) {
        if(!jwtTokenProvider.validateToken(reqDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // access token 에서 member id 가져옴
        Authentication authentication = jwtTokenProvider.getAuthentication(reqDto.getAccessToken());

        // member id 기반으로 refresh token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // refresh token 일치하는지 검사
        if(!refreshToken.getValue().equals(reqDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 신규 토큰 생성
        TokenDto token = jwtTokenProvider.createToken(authentication);

        // 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(token.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return ResultDto.res(HttpStatus.OK, token);
    }
}

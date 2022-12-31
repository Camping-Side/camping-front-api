package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.dto.TokenDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.RefreshToken;
import com.commerce.song.domain.entity.Role;
import com.commerce.song.exception.BadRequestException;
import com.commerce.song.repository.AccountRepository;
import com.commerce.song.repository.RefreshTokenRepository;
import com.commerce.song.repository.RoleRepository;
import com.commerce.song.security.domain.UserContextDto;
import com.commerce.song.security.provider.JwtTokenProvider;
import com.commerce.song.security.token.CustomContextToken;
import com.commerce.song.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResultDto<Long> signup(AccountDto.SignAccountReq reqDto) {
        if(accountRepository.existsByEmail(reqDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Account account = Account.builder()
                .username(reqDto.getUsername())
                .nickname(reqDto.getNickname())
                .phone(reqDto.getPhone())
                .email(reqDto.getEmail())
                .password(passwordEncoder.encode(reqDto.getPassword()))
                .birth(reqDto.getBirth())
                .phone(reqDto.getPhone())
                .activated(true)
                .userRoles(roles)
                .build();

        Account savedAccount = accountRepository.save(account);

        return ResultDto.res(HttpStatus.OK, savedAccount.getId());
    }

    @Transactional
    public ResultDto<TokenDto> login(AccountDto.LoginReq reqDto) {

        Account account = accountRepository.findByEmail(reqDto.getEmail());
        if(account == null || !passwordEncoder.matches(reqDto.getPassword(), account.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        if(!account.isActivated()) {
            throw new BadCredentialsException("expired account");
        }

        Set<Role> userRoles = account.getUserRoles();
        Set<String> roleSet = userRoles.stream().map(Role::getRoleName).collect(Collectors.toSet());

        UserContextDto user = UserContextDto.builder()
                .account(account)
                .username(account.getEmail())
                .password(account.getPassword())
                .roles(roleSet).build();

        CustomContextToken authenticationToken = CustomContextToken.getTokenFromAccountContext(user);

//        Authentication authenticate =
//                authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 인증정보를 기반으로 토큰정보 가져옴
        TokenDto tokenDto = jwtTokenProvider.createToken(authenticationToken);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(reqDto.getEmail())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return ResultDto.res(HttpStatus.OK, tokenDto);
    }

    // 토큰 재발급급
   @Transactional
    public ResultDto<TokenDto> reissue(TokenDto.TokenRequestDto reqDto) throws Exception {
        // 토큰 유효성 체크, 검증 안되면 exception
        jwtTokenProvider.validateToken(reqDto.getRefreshToken());

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

package com.commerce.song.controller;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.dto.TokenDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.security.common.AccountContext;
import com.commerce.song.security.filter.JwtFilter;
import com.commerce.song.security.provider.JwtTokenProvider;
import com.commerce.song.service.AccountService;
import com.commerce.song.service.AuthService;
import com.commerce.song.util.HttpCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

//@CrossOrigin(origins = "http://localhost:3030")
@Api(tags= { " 인증 rest api "})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountService accountService;
    private final AuthService authService;


    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody AccountDto accountDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(accountDto.getEmail(), accountDto.getPassword());

        Authentication authenticate =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 인증정보를 기반으로 토큰정보 가져옴
        TokenDto tokenDto = jwtTokenProvider.createToken(authenticate);
        AccountContext principal = (AccountContext) authenticate.getPrincipal();

        // 토큰 정보를 헤더에 넣어줌
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // Dto 활용해서 Body에도 넣어줌줌
        return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ResultDto<Long>> signup(@Valid @RequestBody AccountDto accountDto) {
        ResultDto<Long> result = authService.signup(accountDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getResultData())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }
}

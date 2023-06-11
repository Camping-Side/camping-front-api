package com.commerce.song.controller;

import com.camping.common.domain.dto.ResultDto;
import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.TokenDto;
import com.commerce.song.security.filter.JwtFilter;
import com.commerce.song.service.AccountService;
import com.commerce.song.service.AuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResultDto<TokenDto>> login(@Valid @RequestBody AccountDto.LoginReq accountDto) {
        ResultDto<TokenDto> result = authService.login(accountDto);

        // 토큰 정보를 헤더에 넣어줌
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + result.getResultData().getAccessToken());

        // Dto 활용해서 Body에도 넣어줌줌
        return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);

    }

    @PostMapping("/sign")
    public ResponseEntity<ResultDto<Long>> signup(@Valid @RequestBody AccountDto.SignAccountReq req) {
        ResultDto<Long> result = authService.signup(req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getResultData())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @PostMapping("/reissue")
    public ResponseEntity<ResultDto<TokenDto>> reissue(@Valid @RequestBody TokenDto.TokenRequestDto reqDto) throws Exception {
        ResultDto<TokenDto> result = authService.reissue(reqDto);

        // 토큰 정보를 헤더에 넣어줌
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + result.getResultData().getAccessToken());

        // Dto 활용해서 Body에도 넣어줌줌
        return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
    }

}

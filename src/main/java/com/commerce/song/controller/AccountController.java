package com.commerce.song.controller;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.dto.TokenDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.security.common.AccountContext;
import com.commerce.song.security.filter.JwtFilter;
import com.commerce.song.security.provider.JwtTokenProvider;
import com.commerce.song.service.AccountService;
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
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3030")
@Api(tags= { " 회원 rest api "})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto.Res> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getUser(id));
    }

    @GetMapping("/me")
    public ResponseEntity<AccountDto.Res> findMyInfo() {
        return ResponseEntity.ok(accountService.getMyInfo());
    }

    @ApiOperation(value="사용자 리스트 조회", notes="사용자 리스트 조회")
    @GetMapping
    public ResponseEntity<ResultDto<Page<AccountDto.ResList>>> findAll(@Valid @ModelAttribute AccountDto.ReqList requestDto) {
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, accountService.findAll(requestDto)));
    }

}

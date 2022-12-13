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

    @ApiOperation(value="계정찾기", notes="이름+휴대폰번호로 계정찾기")
    @PostMapping("/findEmail")
    public ResponseEntity<AccountDto.FindEmailRes> checkEmailDup(@Valid @RequestBody AccountDto.FindEmailReq reqDto) {
        return ResponseEntity.ok(accountService.findEmail(reqDto));
    }

    @ApiOperation(value="비밀번호재설정", notes="아이디+휴대폰번호로 비밀번호 재설정 (재설정 비밀번호 1111)")
    @PostMapping("/resetPassword")
    public ResponseEntity<ResultDto<Long>> resetPassword(@Valid @RequestBody AccountDto.ResetPasswordReq reqDto) {
        return ResponseEntity.ok(accountService.resetPassword(reqDto));
    }

    @ApiOperation(value="이메일중복체크", notes="이메일 중복체크")
    @PostMapping("/checkEmailDup")
    public ResponseEntity<AccountDto.CheckEmailDupRes> checkEmailDup(@Valid @RequestBody AccountDto.CheckEmailDupReq reqDto) {
        return ResponseEntity.ok(accountService.checkEmailDup(reqDto.getEmail()));
    }

    @ApiOperation(value="휴대폰번호중복체크", notes="휴대폰번호 중복체크")
    @PostMapping("/checkPhoneDup")
    public ResponseEntity<AccountDto.CheckPhoneDupRes> checkPhoneDup(@Valid @RequestBody AccountDto.CheckPhoneDupReq reqDto) {
        return ResponseEntity.ok(accountService.checkPhoneDup(reqDto.getPhone()));
    }

}

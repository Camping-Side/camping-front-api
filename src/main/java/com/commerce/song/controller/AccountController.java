package com.commerce.song.controller;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.security.provider.JwtTokenProvider;
import com.commerce.song.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin(origins = "http://localhost:3030")
@Api(tags= { " 회원 rest api "})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResultDto<AccountDto.Res> findById(@PathVariable Long id) {
        return ResultDto.res(accountService.getUser(id));
    }

    @GetMapping("/me")
    public ResultDto<AccountDto.Res> findMyInfo() {
        return ResultDto.res(accountService.getMyInfo());
    }

    @DeleteMapping("/{id}")
    public ResultDto deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResultDto.res();
    }

    @PutMapping("/{id}")
    public ResultDto updateAccount(@PathVariable Long id
            , @RequestBody @Validated AccountDto.UpdateAccountReq req) {
        accountService.updateAccount(req, id);
        return ResultDto.res();
    }

    @ApiOperation(value="사용자 리스트 조회", notes="사용자 리스트 조회")
    @GetMapping
    public ResultDto<Page<AccountDto.ResList>> findAll(@Validated @ModelAttribute AccountDto.ReqList requestDto) {
        return ResultDto.res(accountService.findAll(requestDto));
    }

    @ApiOperation(value="계정찾기", notes="이름+휴대폰번호로 계정찾기")
    @PostMapping("/findEmail")
    public AccountDto.FindEmailRes checkEmailDup(@Validated @RequestBody AccountDto.FindEmailReq reqDto) {
        return accountService.findEmail(reqDto);
    }

    @ApiOperation(value="비밀번호재설정", notes="아이디+휴대폰번호로 비밀번호 재설정 (재설정 비밀번호 1111)")
    @PostMapping("/resetPassword")
    public ResultDto resetPassword(@Valid @RequestBody AccountDto.ResetPasswordReq reqDto) {
        accountService.resetPassword(reqDto);
        return ResultDto.res();
    }

    @ApiOperation(value="이메일중복체크", notes="이메일 중복체크")
    @PostMapping("/checkEmailDup")
    public AccountDto.CheckEmailDupRes checkEmailDup(@Valid @RequestBody AccountDto.CheckEmailDupReq reqDto) {
        return accountService.checkEmailDup(reqDto.getEmail());
    }

    @ApiOperation(value="휴대폰번호중복체크", notes="휴대폰번호 중복체크")
    @PostMapping("/checkPhoneDup")
    public AccountDto.CheckPhoneDupRes checkPhoneDup(@Valid @RequestBody AccountDto.CheckPhoneDupReq reqDto) {
        return accountService.checkPhoneDup(reqDto.getPhone());
    }

}

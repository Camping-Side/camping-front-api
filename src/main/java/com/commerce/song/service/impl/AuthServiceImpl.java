package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.repository.AccountRepository;
import com.commerce.song.service.AccountService;
import com.commerce.song.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

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
}

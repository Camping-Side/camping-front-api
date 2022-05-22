package com.commerce.song.service;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;

public interface AuthService {
    ResultDto<Long> signup(AccountDto reqDto);
}

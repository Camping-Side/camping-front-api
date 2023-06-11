package com.commerce.song.service;

import com.camping.common.domain.dto.ResultDto;
import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.TokenDto;

public interface AuthService {
    ResultDto<Long> signup(AccountDto.SignAccountReq reqDto);
    ResultDto<TokenDto> login(AccountDto.LoginReq reqDto);
    ResultDto<TokenDto> reissue(TokenDto.TokenRequestDto reqDto) throws Exception;
}

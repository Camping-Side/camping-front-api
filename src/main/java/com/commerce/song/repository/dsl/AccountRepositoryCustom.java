package com.commerce.song.repository.dsl;

import com.commerce.song.domain.dto.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountRepositoryCustom {
    Page<AccountDto.ResList> findAllToDtoPage(Pageable pageable, AccountDto.ReqList reqDto);

    Long updatePassword(AccountDto.ResetPasswordReq reqDto, String password);
}

package com.commerce.song.repository.dsl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.repository.custom.AccountRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    @Override
    public Page<AccountDto.ResList> findAllToDtoPage(Pageable pageable, AccountDto.ReqList reqDto) {

        return null;
    }
}

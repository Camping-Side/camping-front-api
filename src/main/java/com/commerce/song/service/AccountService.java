package com.commerce.song.service;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.entity.Account;
import org.springframework.data.domain.Page;

public interface AccountService {
    Account createUser(Account account);

    void modifyUser(AccountDto accountDto);

    Page<AccountDto.ResList> findAll(AccountDto.ReqList requestDto);

    AccountDto getUser(Long id);

    void deleteUser(Long idx);

}

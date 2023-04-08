package com.commerce.song.service;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.entity.Account;
import org.springframework.data.domain.Page;

public interface AccountService {
    Account createUser(Account account);

    void modifyUser(AccountDto accountDto);

    Page<AccountDto.ResList> findAll(AccountDto.ReqList requestDto);

    AccountDto.Res getUser(Long id);
    AccountDto.Res getMyInfo();
    AccountDto.CheckEmailDupRes checkEmailDup(String email);
    AccountDto.CheckPhoneDupRes checkPhoneDup(String phone);
    AccountDto.FindEmailRes findEmail(AccountDto.FindEmailReq requestDto);
    void resetPassword(AccountDto.ResetPasswordReq requestDto);
    void deleteUser(Long idx);
    void deleteAccount(Long id);
    void updateAccount(AccountDto.UpdateAccountReq req, Long id);

}

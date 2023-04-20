package com.commerce.song.service.impl;

import com.camping.common.exception.BadRequestException;
import com.camping.common.util.CustomUtil;
import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.Role;
import com.commerce.song.repository.AccountRepository;
import com.commerce.song.repository.RoleRepository;
import com.commerce.song.service.AccountService;
import com.commerce.song.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Account createUser(Account account) {
        Role role = roleRepository.findByRoleNm("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);
        Account result = accountRepository.save(account);
        return result;
    }

    @Transactional
    @Override
    public void modifyUser(AccountDto accountDto) {
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);

        if(accountDto.getUserRoles() != null){
            Set<Role> roles = new HashSet<>();
            accountDto.getUserRoles().forEach(role -> {
                Role r = roleRepository.findByRoleNm(role.getRoleNm());
                roles.add(r);
            });
            account.setUserRoles(roles);
        }
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountRepository.save(account);
    }

    public AccountDto.Res getUser(Long id) {
        return accountRepository.findById(id)
                .map(AccountDto.Res::new)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    public AccountDto.Res getMyInfo() {
        Account account = accountRepository.findByEmail(SecurityUtil.getCurrentEmail());
        if(account == null) throw new BadRequestException("유저 정보가 없습니다.");
        return new AccountDto.Res(account);
    }

    @Transactional
    public Page<AccountDto.ResList> findAll(AccountDto.ReqList requestDto) {
        return accountRepository.findAllToDtoPage(CustomUtil.convertPageVo(requestDto), requestDto);
    }

    @Override
    public void deleteUser(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto.CheckEmailDupRes checkEmailDup(String email) {
        return new AccountDto.CheckEmailDupRes(accountRepository.existsByEmail(email));
    }

    @Override
    public AccountDto.CheckPhoneDupRes checkPhoneDup(String phone) {
        return new AccountDto.CheckPhoneDupRes(accountRepository.existsByPhone(phone));
    }

    @Override
    public AccountDto.FindEmailRes findEmail(AccountDto.FindEmailReq reqDto) {
        AccountDto.FindEmailRes resDto = new AccountDto.FindEmailRes(new Account());
        try {
            resDto.setEmail(accountRepository.findByUsernameAndPhone(reqDto.getUsername(), reqDto.getPhone()).getEmail());
        } catch (Exception e) {
            throw new RuntimeException("유저 정보가 없습니다.");
        }
        return resDto;
    }

    @Transactional
    @Override
    public void resetPassword(AccountDto.ResetPasswordReq reqDto) {
        accountRepository.updatePassword(reqDto, passwordEncoder.encode("111111"));
    }

    @Transactional
    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 아이디입니다."));
        account.leave();
    }

    @Transactional
    @Override
    public void updateAccount(AccountDto.UpdateAccountReq req, Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 아이디입니다."));
        account.updateByAdmin(req);
    }
}

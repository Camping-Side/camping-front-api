package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.Role;
import com.commerce.song.repository.AccountRepository;
import com.commerce.song.repository.RoleRepository;
import com.commerce.song.service.AccountService;
import com.commerce.song.util.CustomUtil;
import com.commerce.song.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Account createUser(Account account) {
        Role role = roleRepository.findByRoleName("ROLE_USER");
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
                Role r = roleRepository.findByRoleName(role.getRoleName());
                roles.add(r);
            });
            account.setUserRoles(roles);
        }
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public AccountDto.Res getUser(Long id) {
        return accountRepository.findById(id)
                .map(AccountDto.Res::new)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }
    @Transactional(readOnly = true)
    public AccountDto.Res getMyInfo() {
        return accountRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(AccountDto.Res::new)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional
    public Page<AccountDto.ResList> findAll(AccountDto.ReqList requestDto) {
        return accountRepository.findAllToDtoPage(CustomUtil.convertPageVo(requestDto), requestDto);
    }

    @Override
    public void deleteUser(Long id) {
        accountRepository.deleteById(id);
    }

    @Transactional
    @Override
    public AccountDto.CheckPhoneDupRes checkPhoneDup(String phone) {
        return new AccountDto.CheckPhoneDupRes(accountRepository.existsByPhone(phone));
    }

    @Override
    public AccountDto.FindEmailRes findEmail() {
        return null;
    }

    @Override
    public ResultDto<Long> resetPassword() {
        return null;
    }
}

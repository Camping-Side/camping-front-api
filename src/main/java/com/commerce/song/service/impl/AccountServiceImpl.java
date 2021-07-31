package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.Role;
import com.commerce.song.repository.AccountRepository;
import com.commerce.song.repository.RoleRepository;
import com.commerce.song.service.AccountService;
import com.commerce.song.util.CustomUtil;
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

    @Transactional
    public AccountDto getUser(Long id) {

        Account account = accountRepository.findById(id).orElse(new Account());
        ModelMapper modelMapper = new ModelMapper();
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        accountDto.setUserRoles(account.getUserRoles());
        return accountDto;
    }

    @Transactional
    public Page<AccountDto.ResList> findAll(AccountDto.ReqList requestDto) {
        return accountRepository.findAllToDtoPage(CustomUtil.convertPageVo(requestDto), requestDto);
    }

    @Override
    public void deleteUser(Long id) {
        accountRepository.deleteById(id);
    }

}

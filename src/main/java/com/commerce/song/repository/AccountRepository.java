package com.commerce.song.repository;

import com.commerce.song.domain.entity.Account;
import com.commerce.song.repository.dsl.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
    int countByUsername(String username);
    int countByEmail(String email);
    Account findByUsername(String username);
    Account findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    Account findByUsernameAndPhone(String username, String phone);
}

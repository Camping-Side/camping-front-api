package com.commerce.song.repository;

import com.commerce.song.domain.entity.Account;
import com.commerce.song.repository.dsl.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
    int countByUsername(String username);
    int countByEmail(String email);
    Account findByUsername(String username);
    Account findByEmail(String email);
}

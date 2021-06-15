package com.commerce.song.repository;

import com.commerce.song.domain.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    int countByUsername(String username);
    int countByEmail(String email);
    Account findByUsername(String username);
    Account findByEmail(String email);
}

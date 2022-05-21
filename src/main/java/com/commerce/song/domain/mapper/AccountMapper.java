package com.commerce.song.domain.mapper;


import com.commerce.song.domain.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Mapper
@Repository
public interface AccountMapper {
    public Optional<Account> findPostTarget(Map<String, Object> paramMap);
    public Optional<Account> findTarget(Map<String, Object> paramMap);
}

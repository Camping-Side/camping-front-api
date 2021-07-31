package com.commerce.song.repository.dsl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.QAccountDto_ResList;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.repository.custom.AccountRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.commerce.song.domain.entity.QAccount.account;
import static com.commerce.song.domain.entity.QRole.role;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Page<AccountDto.ResList> findAllToDtoPage(Pageable pageable, AccountDto.ReqList reqDto) {

        QueryResults<Account> result = query
                .select(account)
                .from(account)
                .leftJoin(account.userRoles, role)
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<AccountDto.ResList> content = result.getResults().stream()
                .map(AccountDto.ResList::new)
                .collect(Collectors.toList());

        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}

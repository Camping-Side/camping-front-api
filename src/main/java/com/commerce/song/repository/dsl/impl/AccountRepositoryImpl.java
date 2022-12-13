package com.commerce.song.repository.dsl.impl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.entity.Account;
import com.commerce.song.repository.dsl.AccountRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.commerce.song.domain.entity.QAccount.account;
import static com.commerce.song.domain.entity.QRole.role;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Page<AccountDto.ResList> findAllToDtoPage(Pageable pageable, AccountDto.ReqList reqDto) {

        QueryResults<Account> result = query
                .select(account)
                .from(account)
                .leftJoin(account.userRoles, role)
                .where(emailEq(reqDto.getEmail()),
                        usernameEq(reqDto.getUsername()),
                        userRoleEq(reqDto.getRoles())
                        )
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

    @Override
    public Long updatePassword(AccountDto.ResetPasswordReq reqDto) {
        return null;
    }

    private BooleanExpression emailEq(String email) {
        return hasText(email) ? account.email.contains(email) : null;
    }
    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? account.username.contains(username) : null;
    }
    private BooleanExpression userRoleEq(List<String> userRoles) {
        return userRoles.size() != 0 ? account.userRoles.any().roleName.in(userRoles) : null;
    }

}

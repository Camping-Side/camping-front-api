package com.commerce.song.repository.dsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class BoardDslRepository {
//    private final JPAQueryFactory query;
//
//    public Page<Corona> findAll(CoronaSearch coronaSearch, Pageable pageable) {
//        QueryResults<Corona> coronaQueryResults = query.selectFrom(corona)
//                .where(dynamicWhere(coronaSearch), periodBetween(coronaSearch))
//                .limit(pageable.getPageSize())
//                .offset(pageable.getOffset())
//                .orderBy(corona.confirmedId.desc())
//                .fetchResults();
//        return new PageImpl<>(coronaQueryResults.getResults(), pageable, coronaQueryResults.getTotal());
//
//    }
//
//    private BooleanExpression periodBetween(CoronaSearch coronaSearch) {
//
//        if(!StringUtils.hasText(coronaSearch.getFromDt()) && !StringUtils.hasText(coronaSearch.getFromDt())) {
//            return null;
//        }
//        if(!StringUtils.hasText(coronaSearch.getToDt()) && StringUtils.hasText(coronaSearch.getFromDt())) {
//            // >= 시작일자
//            return corona.confirmedDate.goe(coronaSearch.getFromDt().replaceAll("-",""));
//        }
//        if(StringUtils.hasText(coronaSearch.getToDt()) && !StringUtils.hasText(coronaSearch.getFromDt())) {
//            //  <= 종료일자
//            return corona.confirmedDate.loe(coronaSearch.getToDt().replaceAll("-",""));
//        }
//         if(StringUtils.hasText(coronaSearch.getToDt()) && StringUtils.hasText(coronaSearch.getFromDt())) {
//             // 시작일자 <= 일자 <= 종료일자
//             return corona.confirmedDate.between(coronaSearch.getFromDt().replaceAll("-",""), coronaSearch.getToDt().replaceAll("-",""));
//         }
//         return null;
//    }
//
//    private BooleanExpression dynamicWhere(CoronaSearch coronaSearch) {
//        if(StringUtils.isEmpty(coronaSearch.getSearchKeyword()) || (StringUtils.isEmpty(coronaSearch.getSearchValue()) && coronaSearch.getConfirmedType() == null  )) return null;
//
//        if("id".equals(coronaSearch.getSearchKeyword())) {
//            Long value = -1L;
//            try {
//                value = Long.valueOf(coronaSearch.getSearchValue());
//            } catch (NumberFormatException e) {
//                value = -1L;
//            }
//            return coronaSearch.getSearchValue() != null ? corona.confirmedId.eq(value) : null;
//        }
//        if("city".equals(coronaSearch.getSearchKeyword())) {
//            return coronaSearch.getSearchValue() != null ? corona.confirmedCity.like("%"+coronaSearch.getSearchValue() + "%") : null;
//        }
//        if("type".equals(coronaSearch.getSearchKeyword())) {
//            return  coronaSearch.getConfirmedType() != null ? corona.confirmedType.eq(coronaSearch.getConfirmedType()) : null;
//        }
//        if("desc".equals(coronaSearch.getSearchKeyword())) {
//            return coronaSearch.getSearchValue() != null ? corona.description.like("%"+coronaSearch.getSearchValue() + "%") : null;
//        }
//        return null;
//    }
}

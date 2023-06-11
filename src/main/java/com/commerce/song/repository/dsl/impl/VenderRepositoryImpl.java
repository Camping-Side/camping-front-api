package com.commerce.song.repository.dsl.impl;

import com.camping.common.util.dsl.DslDateUtil;
import com.camping.common.util.dsl.DslUtil;
import com.commerce.song.domain.dto.VenderDto;
import com.commerce.song.repository.dsl.VenderRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.commerce.song.domain.entity.QVender.vender;

@RequiredArgsConstructor
public class VenderRepositoryImpl implements VenderRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Page<VenderDto.ResList> findAllToDtoPage(Pageable pageable, VenderDto.ReqList reqDto) {
        QueryResults<VenderDto.ResList> result = query
                .select(Projections.constructor(VenderDto.ResList.class,
                                vender.vdrId
                                , vender.vdrNm
                                , vender.vdrSts
                                , DslDateUtil.getYMDFormat(vender.createdDate, DslDateUtil.READ_DATE_FORMAT)
                                , vender.createdBy
                                , DslDateUtil.getYMDFormat(vender.lastModifiedDate, DslDateUtil.READ_DATE_FORMAT)
                                , vender.lastModifiedBy
                        )
                )
                .from(vender)
                .where(
                        DslUtil.cprEq(vender.vdrSts, reqDto.getVdrSts())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        long total = result.getTotal();

        return new PageImpl<>(result.getResults(), pageable, total);
    }
}

package com.commerce.song.repository.dsl.impl;

import com.commerce.song.domain.dto.BrandDto;
import com.commerce.song.repository.dsl.BrandRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.commerce.song.domain.entity.QBrand.brand;

@RequiredArgsConstructor
public class BrandRepositoryImpl implements BrandRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Page<BrandDto.ResList> findAllToDtoPage(Pageable pageable, BrandDto.ReqList reqDto) {
        QueryResults<BrandDto.ResList> result = query
                .select(
                        Projections.constructor(BrandDto.ResList.class
                                , brand.brandId
                                , brand.brandNm
                                , brand.intro
                                , brand.officeTel
                                , brand.brandUrl
                                , brand.useYn
                                , brand.brandImg
                                , DslDateUtil.getYMDFormat(brand.createdDate, DslDateUtil.READ_DATE_FORMAT)
                                , brand.createdBy
                                , DslDateUtil.getYMDFormat(brand.lastModifiedDate, DslDateUtil.READ_DATE_FORMAT)
                                , brand.lastModifiedBy
                        )
                )
                .from(brand)
                .where(
                        DslUtil.cprNumEq(brand.brandId, reqDto.getBrandId())
                        , DslUtil.cprStrLike(brand.brandNm, reqDto.getBrandNm())
                        , DslUtil.cprStrEq(brand.useYn, reqDto.getUseYn())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        long total = result.getTotal();

        return new PageImpl<>(result.getResults(), pageable, total);
    }
}

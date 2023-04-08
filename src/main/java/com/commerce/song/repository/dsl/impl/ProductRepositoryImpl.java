package com.commerce.song.repository.dsl.impl;

import com.commerce.song.domain.dto.ProductDto;
import com.commerce.song.repository.dsl.ProductRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.commerce.song.domain.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Page<ProductDto.ResList> findAllToDtoPage(Pageable pageable, ProductDto.ReqList reqDto) {

        QueryResults<ProductDto.ResList> result = query
                .select(
                        Projections.constructor(ProductDto.ResList.class,
                                product.productId
                                , product.name
                                , product.taxTp
                                , product.prdTp
                                , product.prdSts
                                , product.productDesc
                                , product.supplyPrc
                                , product.salePrc
                                , product.prdPrc
                                , product.totalCnt
                                , DslDateUtil.getYMDFormat(product.startDate, DslDateUtil.READ_DATE_FORMAT)
                                , DslDateUtil.getYMDFormat(product.endDate, DslDateUtil.READ_DATE_FORMAT)

                        )
                )
                .from(product)
                .where(
                        DslDateUtil.cprStartDate(product.startDate, reqDto.getStartDate()),
                        DslDateUtil.cprEndDate(product.endDate, reqDto.getEndDate()),
                        DslDateUtil.cprStartDate(product.createdDate, reqDto.getStartRegDate()),
                        DslDateUtil.cprEndDate(product.createdDate, reqDto.getEndRegDate()),
                        DslUtil.cprNumEq(product.prdSts, reqDto.getPrdSts()),
                        DslUtil.cprNumEq(product.prdTp, reqDto.getPrdTp()),
                        DslUtil.cprNumEq(product.taxTp, reqDto.getTaxTp()),
                        dynamicSearch(reqDto.getKeywordTp(), reqDto.getKeywordText())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

//        List<ProductDto.ResList> content = result.getResults().stream()
//                .map(item -> mapper.map(item, ProductDto.ResList.class))
//                .collect(Collectors.toList());
        long total = result.getTotal();

        return new PageImpl<>(result.getResults(), pageable, total);
    }

    private BooleanExpression dynamicSearch(Integer keywordType, String keywordText) {
        if(keywordType == null || !StringUtils.hasText(keywordText)) return null;

        // 상품명
        if(keywordType.equals(0)) {
            return DslUtil.cprStrLike(product.name, keywordText);
        }

        return null;
    }

}

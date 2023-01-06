package com.commerce.song.repository.dsl.impl;

import com.commerce.song.domain.dto.ProductDto;
import com.commerce.song.domain.entity.Product;
import com.commerce.song.repository.dsl.ProductRepositoryCustom;
import com.commerce.song.util.dsl.DslDateUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import static com.commerce.song.domain.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Page<ProductDto.ResList> findAllToDtoPage(Pageable pageable, ProductDto.ReqList reqDto) {
        ModelMapper mapper = new ModelMapper();

        QueryResults<Product> result = query
                .select(product)
                .from(product)
                .where(
                        DslDateUtil.cprStartDate(product.startDate, reqDto.getStartDate()),
                        DslDateUtil.cprEndDate(product.endDate, reqDto.getStartDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ProductDto.ResList> content = result.getResults().stream()
                .map(item -> mapper.map(item, ProductDto.ResList.class))
                .collect(Collectors.toList());
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

}

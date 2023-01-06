package com.commerce.song.repository.dsl;

import com.commerce.song.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<ProductDto.ResList> findAllToDtoPage(Pageable pageable, ProductDto.ReqList reqDto);
}

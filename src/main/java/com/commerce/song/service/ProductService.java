package com.commerce.song.service;

import com.commerce.song.domain.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductDto.ResList> findAll(ProductDto.ReqList requestDto);
}

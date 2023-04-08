package com.commerce.song.service;

import com.commerce.song.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Page<ProductDto.ResList> findAll(ProductDto.ReqList requestDto);
    Long createProduct(ProductDto.createProductReq reqDto);

    ResultDto<ProductDto.Res> findById(Long id);
}

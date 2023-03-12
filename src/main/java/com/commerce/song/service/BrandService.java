package com.commerce.song.service;

import com.commerce.song.domain.dto.BrandDto;
import com.commerce.song.domain.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface BrandService {
    Page<BrandDto.ResList> findAll(BrandDto.ReqList req);
}

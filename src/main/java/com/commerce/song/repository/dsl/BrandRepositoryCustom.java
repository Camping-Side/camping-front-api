package com.commerce.song.repository.dsl;

import com.commerce.song.domain.dto.BrandDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandRepositoryCustom {
    Page<BrandDto.ResList> findAllToDtoPage(Pageable pageable, BrandDto.ReqList reqDto);
}

package com.commerce.song.repository.dsl;

import com.commerce.song.domain.dto.VenderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VenderRepositoryCustom {
    Page<VenderDto.ResList> findAllToDtoPage(Pageable pageable, VenderDto.ReqList reqDto);
}

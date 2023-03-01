package com.commerce.song.service;

import com.commerce.song.domain.dto.VenderDto;
import org.springframework.data.domain.Page;

public interface VenderService {
    Page<VenderDto.ResList> findAll(VenderDto.ReqList req);
}

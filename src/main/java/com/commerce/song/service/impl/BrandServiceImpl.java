package com.commerce.song.service.impl;

import com.camping.common.util.CustomUtil;
import com.commerce.song.domain.dto.BrandDto;
import com.commerce.song.repository.BrandRepository;
import com.commerce.song.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Page<BrandDto.ResList> findAll(BrandDto.ReqList req) {
        return brandRepository.findAllToDtoPage(CustomUtil.convertPageVo(req), req);
    }
}

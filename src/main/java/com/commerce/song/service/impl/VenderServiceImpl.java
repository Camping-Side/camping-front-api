package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.VenderDto;
import com.commerce.song.repository.VenderRepository;
import com.commerce.song.service.VenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VenderServiceImpl implements VenderService {
    private final VenderRepository venderRepository;

    @Override
    public Page<VenderDto.ResList> findAll(VenderDto.ReqList req) {
        return venderRepository.findAllToDtoPage(CustomUtil.convertPageVo(req), req);
    }
}

package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.SpecialPlanDto;
import com.commerce.song.service.SpecialPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpecialPlanServiceImpl implements SpecialPlanService {

    @Override
    public Page<SpecialPlanDto.ResSpecialPlan> findAll(SpecialPlanDto.ResSpecialPlan req) {
        return null;
    }


}

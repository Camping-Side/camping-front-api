package com.commerce.song.service;

import com.commerce.song.domain.dto.SpecialPlanDto;
import org.springframework.data.domain.Page;

public interface SpecialPlanService {

    Page<SpecialPlanDto.ResSpecialPlan> findAll(SpecialPlanDto.ResSpecialPlan req);
}

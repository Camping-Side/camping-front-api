package com.commerce.song.controller;

import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.dto.SpecialPlanDto;
import com.commerce.song.service.SpecialPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "특별전 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/specialplans")
public class SpecialController {

    private final SpecialPlanService specialPlanService;

    @ApiOperation(value ="기획전 리스트 조회", notes = "기획전 리스트 조회")
    @GetMapping
    public ResultDto<Page<SpecialPlanDto.ResSpecialPlan>> findAll(@Validated @ModelAttribute SpecialPlanDto.ResSpecialPlan req){
        Page<SpecialPlanDto.ResSpecialPlan> list = specialPlanService.findAll(req);
        return ResultDto.res(list);
    }
}

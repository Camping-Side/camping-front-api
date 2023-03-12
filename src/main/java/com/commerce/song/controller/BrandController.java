package com.commerce.song.controller;

import com.commerce.song.domain.dto.BrandDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "브랜드 rest api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    @ApiOperation(value = "브랜드 리스트 조회")
    @GetMapping
    public ResultDto<Page<BrandDto.ResList>> findAll(@Validated @ModelAttribute BrandDto.ReqList req) {
        return ResultDto.res(brandService.findAll(req));
    }
}

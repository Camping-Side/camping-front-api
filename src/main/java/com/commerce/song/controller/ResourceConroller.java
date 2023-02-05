package com.commerce.song.controller;

import com.commerce.song.domain.dto.ResourcesDto;
import com.commerce.song.domain.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "자원 rest api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resources")
public class ResourceConroller {

    @ApiOperation(value = "자원 리스트 조회")
    @GetMapping
    public ResultDto<Page<ResourcesDto.ResList>> findAll(@Validated @ModelAttribute ResourcesDto.ReqList req) {
        return ResultDto.res();
    }
}

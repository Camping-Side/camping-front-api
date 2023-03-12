package com.commerce.song.controller;

import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.dto.VenderDto;
import com.commerce.song.service.VenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "벤더사 rest api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/venders")
public class VenderController {
    private final VenderService venderService;

    @ApiOperation(value = "벤더 리스트 조회")
    @GetMapping
    public ResultDto<Page<VenderDto.ResList>> findAll(@Validated @ModelAttribute VenderDto.ReqList req) {
        return ResultDto.res(venderService.findAll(req));
    }
}

package com.commerce.song.controller;

import com.commerce.song.domain.dto.AwsDto;
import com.commerce.song.service.AwsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags= { " AWS rest api "})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/aws")
public class AwsController {
    private final AwsService awsService;

    @PostMapping(value = "/upload/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResultDto<String> uploadImage(@ModelAttribute AwsDto.ImageUploadReq req) throws IOException {
        ResultDto<AwsDto.FileUploadRes> result = awsService.uploadImage(req);
        return ResultDto.res(result.getResultData().getFileUrl());
    }

    @PostMapping(value = "/delete/image")
    public ResultDto<AwsCode> deleteImage(@RequestBody AwsDto.ImgDeleteReq req) throws Exception {
        return awsService.deleteImage(req);
    }

}

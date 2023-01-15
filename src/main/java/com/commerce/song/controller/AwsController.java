package com.commerce.song.controller;

import com.commerce.song.domain.dto.AwsDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.service.AwsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags= { " AWS rest api "})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/aws")
public class AwsController {
    private final AwsService awsService;

    @PostMapping(value = "/upload/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResultDto<String> uploadImage(@ModelAttribute AwsDto.ImageUploadReq req) throws IOException {
        return awsService.uploadImage(req);
    }

}

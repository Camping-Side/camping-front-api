package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AwsDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.exception.AwsUploadException;
import com.commerce.song.service.AwsService;
import com.commerce.song.util.enums.rescode.AwsCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile({"local"})
public class AwsServiceLocalImpl implements AwsService {
    @Override
    public ResultDto<AwsDto.FileUploadRes> uploadFile(String folder, MultipartFile file) throws IOException {
        throw new AwsUploadException(AwsCode.AWS_ACCESS_LOCAL_FAILED);
    }

    @Override
    public ResultDto<String> uploadImage(AwsDto.ImageUploadReq req) throws IOException {
        throw new AwsUploadException(AwsCode.AWS_ACCESS_LOCAL_FAILED);
    }
}

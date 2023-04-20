package com.commerce.song.service;

import com.camping.common.domain.dto.ResultDto;
import com.camping.common.domain.enums.rescode.AwsCode;
import com.commerce.song.domain.dto.AwsDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsService {
    ResultDto<AwsDto.FileUploadRes> uploadFile(String folder, MultipartFile file) throws IOException;
    ResultDto<AwsDto.FileUploadRes> uploadImage(AwsDto.ImageUploadReq req) throws IOException ;
    ResultDto<AwsCode> deleteImage(AwsDto.ImgDeleteReq req) throws IOException;
}

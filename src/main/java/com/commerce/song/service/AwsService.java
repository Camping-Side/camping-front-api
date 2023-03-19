package com.commerce.song.service;

import com.commerce.song.domain.dto.AwsDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.enums.rescode.AwsCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsService {
    ResultDto<AwsDto.FileUploadRes> uploadFile(String folder, MultipartFile file) throws IOException;
    ResultDto<AwsDto.FileUploadRes> uploadImage(AwsDto.ImageUploadReq req) throws IOException ;
    ResultDto<AwsCode> deleteImage(AwsDto.ImgDeleteReq req) throws IOException;
}

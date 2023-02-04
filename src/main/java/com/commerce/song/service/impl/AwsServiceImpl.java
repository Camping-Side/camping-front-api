package com.commerce.song.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.commerce.song.domain.dto.AwsDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.entity.CommImg;
import com.commerce.song.exception.AwsUploadException;
import com.commerce.song.repository.CommImgRepository;
import com.commerce.song.service.AwsService;
import com.commerce.song.util.DateUtil;
import com.commerce.song.util.FileUtil;
import com.commerce.song.util.enums.rescode.AwsCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile({"dev", "real"})
public class AwsServiceImpl implements AwsService {
    private final AmazonS3Client amazonS3Client;
    private final CommImgRepository commImgRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${my.server.ip}")
    private String serverIp;

    @Override
    public ResultDto<AwsDto.FileUploadRes> uploadImage(AwsDto.ImageUploadReq req) throws IOException {
        MultipartFile file = req.getImage();
        if(!FileUtil.isImgFile(file.getInputStream(), file.getOriginalFilename())) {
            throw new AwsUploadException(AwsCode.AWS_IMAGE_TYPE_FAILED);
        }

        ResultDto<AwsDto.FileUploadRes> uploadResult = uploadFile(req.getFolder(), file);

        if(!AwsCode.AWS_FILE_UPLOAD_SUCCESS.getCode().equals(uploadResult.getStatusCode())) {
            throw new AwsUploadException();
        }

        String url = uploadResult.getResultData().getFileUrl();
        uploadResult.getResultData().setFileUrl(serverIp + "/image/" + url);
        return uploadResult;
    }

    @Override
    public ResultDto<AwsDto.FileUploadRes> uploadFile(String folder, MultipartFile file) throws IOException {
        if(!StringUtils.hasText(folder)) folder = "default";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);
        String storePath = folder + "/" + DateUtil.getNow(DateUtil.FORMAT_DATE_DASH) + "/"
                + DateUtil.getNow(DateUtil.FORMAT_TIME_DASH) + new Random().nextInt(1000) + "." + ext;

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, storePath, inputStream, objectMetadata)
                    .withCannedAcl((CannedAccessControlList.Private)));
        }


        AwsDto.FileUploadRes res = AwsDto.FileUploadRes.builder()
                .awsUrl(amazonS3Client.getUrl(bucket, storePath).toString())
                .fileUrl(storePath)
                .build();

        return ResultDto.res(AwsCode.AWS_FILE_UPLOAD_SUCCESS, res);
    }

    @Override
    @Transactional
    public ResultDto<AwsCode> deleteImage(AwsDto.ImgDeleteReq req){
        Long commImgId = req.getCommImgId();
        CommImg commImg = commImgRepository.findById(commImgId)
                .orElseThrow(() -> new AwsUploadException(AwsCode.AWS_FILE_FOUND_FAILED));

        commImgRepository.deleteById(commImg.getImgId());

        try {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, commImg.getAwsPath()));
        } catch(Exception e) {
            throw new AwsUploadException(AwsCode.AWS_FILE_DELETE_FAILED);
        }

        return ResultDto.res(AwsCode.AWS_FILE_DELETE_SUCCESS);
    }
}

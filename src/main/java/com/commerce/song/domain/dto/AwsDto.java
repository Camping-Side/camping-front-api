package com.commerce.song.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AwsDto {
    @Data
    public static class ImageUploadReq {
        @ApiModelProperty(value = "폴더명")
        private String folder;
        @JsonIgnore
        private MultipartFile image;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileUploadRes {
        @ApiModelProperty(value = "AWS URL")
        private String awsUrl;
        @ApiModelProperty(value = "AWS 도메인 제외 URL")
        private String fileUrl;
    }
}

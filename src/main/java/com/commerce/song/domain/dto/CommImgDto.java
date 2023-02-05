package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CommImgDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductImgDto {
        @ApiModelProperty(value = "이미지 id", example = "0")
        private Long imgId;
        @ApiModelProperty(value = "순서", example = "0")
        private Integer seq;
        @ApiModelProperty(value = "이미지 경로")
        private String imgPath;
    }
}

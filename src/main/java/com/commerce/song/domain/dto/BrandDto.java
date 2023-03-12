package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class BrandDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRes {
        @ApiModelProperty(value = "브랜드 id", example = "0")
        private Long brandId;
        @ApiModelProperty(value = "브랜드명")
        private String brandNm;
    }
}

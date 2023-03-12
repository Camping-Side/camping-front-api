package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class VenderDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRes {
        @ApiModelProperty(value = "벤더 id", example = "0")
        private Long vdrId;
        @ApiModelProperty(value = "벤더명")
        private String vdrNm;
    }
}

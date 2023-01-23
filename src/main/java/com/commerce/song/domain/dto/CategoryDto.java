package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CategoryDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRes {
        @ApiModelProperty(value = "카테고리 id")
        private Long categoryId;

        @ApiModelProperty(value = "카테고리 이름")
        private String categoryName;
    }
}

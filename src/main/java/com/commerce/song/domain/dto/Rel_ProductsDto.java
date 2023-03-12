package com.commerce.song.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Rel_ProductsDto {
    private Long relId;
    private Long productId;
    private Long eventId;
    private String relCategory;
    private List<Rel_ProductsDto> relProductsDtoList = new ArrayList<>();

    @QueryProjection
    public Rel_ProductsDto(Long relId, Long productId, Long eventId, String relCategory, List<Rel_ProductsDto> relProductsDtoList) {
        this.relId = relId;
        this.productId = productId;
        this.eventId = eventId;
        this.relCategory = relCategory;
        this.relProductsDtoList = relProductsDtoList;
    }

    @Data
    public static class ResRelProduct {
        @ApiModelProperty(value ="연관상품시퀀스", example = "0")
        private Long relId;
        @ApiModelProperty(value="연관상품번호", example = "0")
        private Long productId;
        @ApiModelProperty(value = "연관이벤트번호", example = "0")
        private Long eventId;
        @ApiModelProperty(value = "연관상품카테고리", example = "")
        private String relCategory;
        @ApiModelProperty(value = "연관상품리스트")
        private List<Rel_ProductsDto> relProductsDtoList = new ArrayList<>();

        //순서 컬럼 추가

    }


}

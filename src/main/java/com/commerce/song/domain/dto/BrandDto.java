package com.commerce.song.domain.dto;

import com.camping.common.domain.dto.BaseDto;
import com.camping.common.domain.dto.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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

    @Data
    public static class ReqList extends PageDto {
        @ApiModelProperty(value = "브랜드 id", example = "0")
        private Long brandId;
        @ApiModelProperty(value = "브랜드명")
        private String brandNm;
        @ApiModelProperty(value = "사용여부")
        private String useYn;
    }

    @Setter
    @Getter
    public static class ResList extends BaseDto {
        @ApiModelProperty(value = "브랜드 id")
        private Long brandId;
        @ApiModelProperty(value = "브랜드명")
        private String brandNm;
        @ApiModelProperty(value = "한줄소개")
        private String intro;
        @ApiModelProperty(value = "대표 전화번호")
        private String officeTel;
        @ApiModelProperty(value = "브랜드 url")
        private String brandUrl;
        @ApiModelProperty(value = "사용여부")
        private String useYn;
        @ApiModelProperty(value = "브랜드 이미지 url")
        private String brandImg;

        public ResList(Long brandId, String brandNm, String intro, String officeTel, String brandUrl
                       , String useYn, String brandImg
                , String createdDate, Long createdBy, String lastModifiedDate, Long lastModifiedBy) {
            super(createdDate, createdBy, lastModifiedDate, lastModifiedBy);
            this.brandId = brandId;
            this.brandNm = brandNm;
            this.intro = intro;
            this.officeTel = officeTel;
            this.brandUrl = brandUrl;
            this.useYn = useYn;
            this.brandImg = brandImg;
        }
    }
}

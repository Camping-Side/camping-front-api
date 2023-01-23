package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionDto {

    private Long optId;
    private Long productId;
    private String optName;
    private String optValue1;
    private String optValue2;
    private String optValue3;
    private Integer optSupplyPrc;
    private Integer optPrc;
    private Integer optCnt;

    @Data
    public static class CreateProductOptionReq {

        @ApiModelProperty(value = "상품옵션값1")
        @NotBlank(message = "옵션값1은 필수입니다.")
        private String optValue1;

        @ApiModelProperty(value = "상품옵션값2")
        private String optValue2;

        @ApiModelProperty(value = "상품옵션값3")
        private String optValue3;

        @ApiModelProperty(value = "옵션 공급가")
        private Integer optSupplyPrc;

        @ApiModelProperty(value = "옵션 금액")
        private Integer optPrc;

        @ApiModelProperty(value = "옵션 재고수량")
        private Integer optCnt;
    }

    @Data
    public static class ProductRes {
        @ApiModelProperty(value = "상품옵션아이디")
        private Long optId;

        @ApiModelProperty(value = "상품옵션값1")
        private String optValue1;

        @ApiModelProperty(value = "상품옵션값2")
        private String optValue2;

        @ApiModelProperty(value = "상품옵션값3")
        private String optValue3;

        @ApiModelProperty(value = "옵션 공급가")
        private Integer optSupplyPrc;

        @ApiModelProperty(value = "옵션 금액")
        private Integer optPrc;

        @ApiModelProperty(value = "옵션 재고수량")
        private Integer optCnt;

    }
}

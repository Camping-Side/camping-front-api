package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.Brand;
import com.commerce.song.domain.entity.Category;
import com.commerce.song.domain.entity.Product;
import com.commerce.song.domain.entity.Vender;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private String taxTp;
    private String prdTp;
    private String prdSts;
    private String productDesc;
    private Integer supplyPrc;
    private Integer salePrc;
    private Integer prdPrc;
    private Integer totalCnt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
//    private Category category;
//    private Brand brand;
//    private Vender vender;

    @Data
    public static class ReqList extends PageDto {
        @ApiModelProperty(value = "판매시작일 8자리 YYYYMMDD")
        @Size(min = 8, max = 8, message = "시작일 8자리를 입력해주세요.")
        private String startDate;
        @ApiModelProperty(value = "판매종료일 8자리 YYYYMMDD")
        @Size(min = 8, max = 8, message = "종료일 8자리를 입력해주세요.")
        private String endDate;
        @ApiModelProperty(value = "시작 등록일 8자리 YYYYMMDD")
        @Size(min = 8, max = 8, message = "시작일 8자리를 입력해주세요.")
        private String startRegDate;
        @ApiModelProperty(value = "마지막 등록일 8자리 YYYYMMDD")
        @Size(min = 8, max = 8, message = "종료일 8자리를 입력해주세요.")
        private String endRegDate;
        @ApiModelProperty(value = "상품상태값(0: 등록, 1: 판매중, 2: 판매중지)")
        private Integer prdSts;
        @ApiModelProperty(value = "상품 타입(0: 일반)")
        private Integer prdTp;
        @ApiModelProperty(value = "과세 타입(0: 과세, 1: 면세, 2: 영세)")
        private Integer taxTp;
        @ApiModelProperty(value = "검색 키워드 타입(0: 상품명)")
        private Integer keywordType;
        @ApiModelProperty(value = "검색 키워드")
        private String keywordText;
//        @ApiModelProperty(value = "카테고리 id")
//        private Long categoryId;
    }

    @Data
    public static class ResList{
        @ApiModelProperty(value = "상품번호")
        private Long productId;
        @ApiModelProperty(value = "상품명")
        private String name;
        @ApiModelProperty(value = "과세타입")
        private String taxTp;
        @ApiModelProperty(value = "상품타입")
        private String prdTp;
        @ApiModelProperty(value = "상품상태")
        private String prdSts;
        @ApiModelProperty(value = "상품설명")
        private String productDesc;
        @ApiModelProperty(value = "공급가")
        private Integer supplyPrc;
        @ApiModelProperty(value = "판매가")
        private Integer salePrc;
        @ApiModelProperty(value = "소비자가")
        private Integer prdPrc;
        @ApiModelProperty(value = "남은 재고수량")
        private Integer totalCnt;
        @ApiModelProperty(value = "판매시작일 8자리 YYYYMMDD")
        private String startDate;
        @ApiModelProperty(value = "판매종료일 8자리 YYYYMMDD")
        private String endDate;
    }

}

package com.commerce.song.domain.dto;

import com.camping.common.domain.dto.PageDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
        @ApiModelProperty(value = "상품상태값(0: 등록, 1: 판매중, 2: 판매중지)", example = "0")
        private Integer prdSts;
        @ApiModelProperty(value = "상품 타입(0: 일반)", example = "0")
        private Integer prdTp;
        @ApiModelProperty(value = "과세 타입(0: 과세, 1: 면세, 2: 영세)", example = "0")
        private Integer taxTp;
        @ApiModelProperty(value = "검색 키워드 타입(0: 상품명)", example = "0")
        private Integer keywordTp;
        @ApiModelProperty(value = "검색 키워드")
        private String keywordText;
//        @ApiModelProperty(value = "카테고리 id")
//        private Long categoryId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResList{
        @ApiModelProperty(value = "상품번호", example = "0")
        private Long productId;
        @ApiModelProperty(value = "상품명")
        private String name;
        @ApiModelProperty(value = "과세타입(0: 과세, 1: 면세, 2: 영세)", example = "0")
        private Integer taxTp;
        @ApiModelProperty(value = "상품타입(0: 일반)", example = "0")
        private Integer prdTp;
        @ApiModelProperty(value = "상품상태(0: 등록, 1: 판매중, 2: 판매중지)", example = "0")
        private Integer prdSts;
        @ApiModelProperty(value = "상품설명")
        private String productDesc;
        @ApiModelProperty(value = "공급가", example = "0")
        private Integer supplyPrc;
        @ApiModelProperty(value = "판매가", example = "0")
        private Integer salePrc;
        @ApiModelProperty(value = "소비자가", example = "0")
        private Integer prdPrc;
        @ApiModelProperty(value = "남은 재고수량", example = "0")
        private Integer totalCnt;
        @ApiModelProperty(value = "판매시작일 8자리 YYYYMMDD")
        private String startDate;
        @ApiModelProperty(value = "판매종료일 8자리 YYYYMMDD")
        private String endDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res {
        @ApiModelProperty(value = "상품번호", example = "0")
        private Long productId;

        @ApiModelProperty(value = "상품명")
        private String name;

        @ApiModelProperty(value = "과세타입(0: 과세, 1: 면세, 2: 영세)", example = "0")
        private Integer taxTp;

        @ApiModelProperty(value = "상품타입(0: 일반)", example = "0")
        private Integer prdTp;

        @ApiModelProperty(value = "상품상태(0: 등록, 1: 판매중, 2: 판매중지)", example = "0")
        private Integer prdSts;

        @ApiModelProperty(value = "상품설명")
        private String productDesc;

        @ApiModelProperty(value = "공급가", example = "0")
        private Integer supplyPrc;

        @ApiModelProperty(value = "판매가", example = "0")
        private Integer salePrc;

        @ApiModelProperty(value = "소비자가", example = "0")
        private Integer prdPrc;

        @ApiModelProperty(value = "재고수량", example = "0")
        private Integer totalCnt;

        @ApiModelProperty(value = "판매시작일 8자리 YYYY-MM-DD")
        private String startDate;

        @ApiModelProperty(value = "판매종료일 8자리 YYYY-MM-DD")
        private String endDate;

        @ApiModelProperty(value = "카테고리")
        private CategoryDto.ProductRes category;

        @ApiModelProperty(value = "브랜드")
        private BrandDto.ProductRes brand;

        @ApiModelProperty(value = "벤더 id")
        private VenderDto.ProductRes vender;

        @ApiModelProperty(value = "택배사코드")
        private String delryCd;

        @ApiModelProperty(value = "배송비 부과 타입(1: 조건부 무료, 2: 유료)")
        private String delryTp;

        @ApiModelProperty(value = "배송비 부과 기준시작금액", example = "0")
        private Integer delryBaseStartPrc;

        @ApiModelProperty(value = "기본 배송비", example = "0")
        private Integer delryPrc;

        @ApiModelProperty(value = "도서산간 배송비", example = "0")
        private Integer delrySidePrc;

        @ApiModelProperty(value = "제주도 배송비", example = "0")
        private Integer delryJejuPrc;

        @ApiModelProperty(value = "출고지 주소")
        private String delryOutAddr;

        @ApiModelProperty(value = "출고지 상세주소")
        private String delryOutAddr2;

        @ApiModelProperty(value = "반품/교환 주소")
        private String delryRefAddr;

        @ApiModelProperty(value = "반품/교환 상세주소")
        private String delryRefAddr2;

        @ApiModelProperty(value = "옵션타입(0: 단건형, 1: 단독형, 2: 조합형)", example = "0")
        private Integer optTp;

        @ApiModelProperty(value = "옵션타이틀1")
        private String optTitle1;

        @ApiModelProperty(value = "옵션타이틀2")
        private String optTitle2;

        @ApiModelProperty(value = "옵션타이틀3")
        private String optTitle3;

        @ApiModelProperty(value = "상품 옵션 리스트")
        private List<ProductOptionDto.ProductRes> productOptions;

        @ApiModelProperty(value = "에디터 업로드 이미지 리스트")
        private Set<CommImgDto.ProductImgDto> commImgs;


    }



    @Data
    public static class createProductReq {
        @ApiModelProperty(value = "상품명", required = true)
        @NotBlank(message = "상품명을 입력해주세요.")
        private String name;

        @ApiModelProperty(value = "과세타입(0: 과세, 1: 면세, 2: 영세)", example = "0", required = true)
        @Max(value = 2, message = "과세타입은 2 이하만 가능합니다.")
        @Min(value = 0, message = "과세타입은 0 이상만 가능합니다.")
        @NotNull(message = "과세타입은 필수값입니다.")
        private Integer taxTp;

        @ApiModelProperty(value = "상품타입(0: 일반)", example = "0", required = true)
        @Max(value = 0, message = "상품타입은 0 이하만 가능합니다.")
        @Min(value = 0, message = "상품타입은 0 이상만 가능합니다.")
        @NotNull(message = "상품타입은 필수값입니다.")
        private Integer prdTp;

        @ApiModelProperty(value = "상품상태(0: 등록, 1: 판매중, 2: 판매중지)", example = "0", required = true)
        @Max(value = 2, message = "상품상태은 2 이하만 가능합니다.")
        @Min(value = 0, message = "상품상태은 0 이상만 가능합니다.")
        @NotNull(message = "상품상태은 필수값입니다.")
        private Integer prdSts;

        @ApiModelProperty(value = "상품설명")
        private String productDesc;

        @ApiModelProperty(value = "공급가", example = "0", required = true)
        @PositiveOrZero(message = "공급가는 양수와 0만 가능합니다.")
        private Integer supplyPrc;

        @ApiModelProperty(value = "판매가", example = "0", required = true)
        @PositiveOrZero(message = "판매가는 양수와 0만 가능합니다.")
        private Integer salePrc;

        @ApiModelProperty(value = "소비자가", example = "0", required = true)
        @PositiveOrZero(message = "소비자가는 양수와 0만 가능합니다.")
        private Integer prdPrc;

        @ApiModelProperty(value = "재고수량", example = "0", required = true)
        @PositiveOrZero(message = "재고수량은 양수와 0만 가능합니다.")
        private Integer totalCnt;

        @ApiModelProperty(value = "판매시작일 8자리 YYYYMMDD", required = true)
        @Size(min = 8, max = 8, message = "판매시작일 8자리를 입력해주세요.")
        private String startDate;

        @ApiModelProperty(value = "판매종료일 8자리 YYYYMMDD", required = true)
        @Size(min = 8, max = 8, message = "판매종료일 8자리를 입력해주세요.")
        private String endDate;

        @ApiModelProperty(value = "카테고리 id", example = "0", required = true)
        @Positive(message = "카테고리 id는 양수만 가능합니다.")
        @NotNull(message = "카테고리 id는 필수값입니다.")
        private Long categoryId;

        @ApiModelProperty(value = "브랜드 id", example = "0", required = true)
        @Positive(message = "브랜드 id는 양수만 가능합니다.")
        @NotNull(message = "브랜드 id는 필수값입니다.")
        private Long brandId;

        @ApiModelProperty(value = "벤더 id", example = "0", required = true)
        @Positive(message = "벤더 id는 양수만 가능합니다.")
        @NotNull(message = "벤더 id는 필수값입니다.")
        private Long vdrId;

        @ApiModelProperty(value = "택배사코드", required = true)
        @NotBlank(message = "택배사코드는 필수값입니다.")
        private String delryCd;

        @ApiModelProperty(value = "배송비 부과 타입(1: 조건부 무료, 2: 유료)", required = true)
        @NotBlank(message = "배송비 부과 타입은 필수값입니다.")
        private String delryTp;

        @ApiModelProperty(value = "배송비 부과 기준시작금액", example = "0", required = true)
        @PositiveOrZero
        @NotNull(message = "배송비 부과 기준시작금액은 필수입니다.")
        private Integer delryBaseStartPrc;

        @ApiModelProperty(value = "기본 배송비", example = "0", required = true)
        @NotNull(message = "배송비는 필수값입니다.")
        @PositiveOrZero
        private Integer delryPrc;

        @ApiModelProperty(value = "도서산간 배송비", example = "0", required = true)
        @NotNull(message = "도서산간 배송비는 필수값입니다.")
        @PositiveOrZero
        private Integer delrySidePrc;

        @ApiModelProperty(value = "제주도 배송비", example = "0", required = true)
        @NotNull(message = "제주도 배송비는 필수값입니다.")
        @PositiveOrZero
        private Integer delryJejuPrc;

        @ApiModelProperty(value = "출고지 주소", required = true)
        @NotBlank(message = "출고지 주소는 필수입니다.")
        private String delryOutAddr;

        @ApiModelProperty(value = "출고지 상세주소", required = true)
        @NotBlank(message = "출고지 상세주소는 필수입니다.")
        private String delryOutAddr2;

        @ApiModelProperty(value = "반품/교환 주소", required = true)
        @NotBlank(message = "반품/교환 주소는 필수입니다.")
        private String delryRefAddr;

        @ApiModelProperty(value = "반품/교환 상세주소", required = true)
        @NotBlank(message = "반품/교환 상세주소는 필수입니다.")
        private String delryRefAddr2;

        @ApiModelProperty(value = "옵션타입(0: 단건형, 1: 단독형, 2: 조합형)", example = "0", required = true)
        @NotNull(message = "옵션 타입은 필수입니다.")
        private Integer optTp;

        @ApiModelProperty(value = "옵션타이틀1")
        private String optTitle1;

        @ApiModelProperty(value = "옵션타이틀2")
        private String optTitle2;

        @ApiModelProperty(value = "옵션타이틀3")
        private String optTitle3;

        @ApiModelProperty(value = "상품 이미지파일(최대 5개)")
        @JsonIgnore
        private List<MultipartFile> files;

        @ApiModelProperty(value = "에디터 업로드 이미지 리스트")
        private List<Long> uploadImgIds;

        @ApiModelProperty(value = "상품 옵션 리스트")
        private List<ProductOptionDto.CreateProductOptionReq> optionList;



    }

    @Data
    public static class updateProductReq {
        @ApiModelProperty(value = "상품명", required = true)
        @NotBlank(message = "상품명을 입력해주세요.")
        private String name;

        @ApiModelProperty(value = "과세타입(0: 과세, 1: 면세, 2: 영세)", example = "0", required = true)
        @Max(value = 2, message = "과세타입은 2 이하만 가능합니다.")
        @Min(value = 0, message = "과세타입은 0 이상만 가능합니다.")
        @NotNull(message = "과세타입은 필수값입니다.")
        private Integer taxTp;

        @ApiModelProperty(value = "상품상태(0: 등록, 1: 판매중, 2: 판매중지)", example = "0", required = true)
        @Max(value = 2, message = "상품상태는 2 이하만 가능합니다.")
        @Min(value = 0, message = "상품상태는 0 이상만 가능합니다.")
        @NotNull(message = "상품상태는 필수값입니다.")
        private Integer prdSts;

        @ApiModelProperty(value = "상품설명")
        private String productDesc;

        @ApiModelProperty(value = "공급가", example = "0", required = true)
        @PositiveOrZero(message = "공급가는 양수와 0만 가능합니다.")
        private Integer supplyPrc;

        @ApiModelProperty(value = "판매가", example = "0", required = true)
        @PositiveOrZero(message = "판매가는 양수와 0만 가능합니다.")
        private Integer salePrc;

        @ApiModelProperty(value = "소비자가", example = "0", required = true)
        @PositiveOrZero(message = "소비자가는 양수와 0만 가능합니다.")
        private Integer prdPrc;

        @ApiModelProperty(value = "재고수량", example = "0", required = true)
        @PositiveOrZero(message = "재고수량은 양수와 0만 가능합니다.")
        private Integer totalCnt;

        @ApiModelProperty(value = "판매시작일 8자리 YYYYMMDD", required = true)
        @Size(min = 8, max = 8, message = "판매시작일 8자리를 입력해주세요.")
        private String startDate;

        @ApiModelProperty(value = "판매종료일 8자리 YYYYMMDD", required = true)
        @Size(min = 8, max = 8, message = "판매종료일 8자리를 입력해주세요.")
        private String endDate;

        @ApiModelProperty(value = "카테고리 id", example = "0")
        @Positive(message = "카테고리 id는 양수만 가능합니다.")
        @NotNull(message = "카테고리 id는 필수값입니다.")
        private Long categoryId;

        @ApiModelProperty(value = "택배사코드", required = true)
        @NotBlank(message = "택배사코드는 필수값입니다.")
        private String delryCd;

        @ApiModelProperty(value = "배송비 부과 타입(1: 조건부 무료, 2: 유료)", required = true)
        @NotBlank(message = "배송비 부과 타입은 필수값입니다.")
        private String delryTp;

        @ApiModelProperty(value = "배송비 부과 기준시작금액", example = "0", required = true)
        @PositiveOrZero
        @NotNull(message = "배송비 부과 기준시작금액은 필수입니다.")
        private Integer delryBaseStartPrc;

        @ApiModelProperty(value = "기본 배송비", example = "0", required = true)
        @NotNull(message = "배송비는 필수값입니다.")
        @PositiveOrZero
        private Integer delryPrc;

        @ApiModelProperty(value = "도서산간 배송비", example = "0", required = true)
        @NotNull(message = "도서산간 배송비는 필수값입니다.")
        @PositiveOrZero
        private Integer delrySidePrc;

        @ApiModelProperty(value = "제주도 배송비", example = "0", required = true)
        @NotNull(message = "제주도 배송비는 필수값입니다.")
        @PositiveOrZero
        private Integer delryJejuPrc;

        @ApiModelProperty(value = "출고지 주소", required = true)
        @NotBlank(message = "출고지 주소는 필수입니다.")
        private String delryOutAddr;

        @ApiModelProperty(value = "출고지 상세주소", required = true)
        @NotBlank(message = "출고지 상세주소는 필수입니다.")
        private String delryOutAddr2;

        @ApiModelProperty(value = "반품/교환 주소", required = true)
        @NotBlank(message = "반품/교환 주소는 필수입니다.")
        private String delryRefAddr;

        @ApiModelProperty(value = "반품/교환 상세주소", required = true)
        @NotBlank(message = "반품/교환 상세주소는 필수입니다.")
        private String delryRefAddr2;

        @ApiModelProperty(value = "옵션타입(0: 단건형, 1: 단독형, 2: 조합형)", example = "0", required = true)
        @NotNull(message = "옵션 타입은 필수입니다.")
        private Integer optTp;

        @ApiModelProperty(value = "옵션타이틀1")
        private String optTitle1;

        @ApiModelProperty(value = "옵션타이틀2")
        private String optTitle2;

        @ApiModelProperty(value = "옵션타이틀3")
        private String optTitle3;

        @ApiModelProperty(value = "상품 이미지파일(최대 5개)")
        @JsonIgnore
        private List<MultipartFile> files;

        @ApiModelProperty(value = "상품 이미지 리스트")
        private List<Long> uploadImgIds;

        @ApiModelProperty(value = "이미지 삭제 리스트")
        private List<Long> deleteImgIds;

        @ApiModelProperty(value = "상품 옵션 등록 리스트")
        private List<ProductOptionDto.CreateProductOptionReq> optionList;

        @ApiModelProperty(value = "상품 옵션 삭제 리스트")
        private List<Long> deleteOptionList;
    }

}

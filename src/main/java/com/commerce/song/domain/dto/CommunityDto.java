package com.commerce.song.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDto {
    private Long communityId;
    private Integer code;
    private String content;
    private String title;
    private String location;
    private Integer status;
    private Integer likeCnt;
    private LocalDateTime createdDate;

    @Data
    public static class ReqList extends PageDto{
        @ApiModelProperty(value = "시작 등록일 8자리 YYYYMMDD")
        @Size(min = 8, max = 8, message = "시작일 8자리를 입력해주세요.")
        private String startRegDate;
        @ApiModelProperty(value = "마지막 등록일 8자리 YYYYMMDD")
        @Size(min = 8, max = 8, message = "종료일 8자리를 입력해주세요.")
        private String endRegDate;
        @ApiModelProperty(value = "게시글 타입", example = "전체,장비,공지사항,음식")
        private Integer code;
        @ApiModelProperty(value = "노출 여부", example = "게시 여부(0:비공개,1:게시) ")
        private Integer status;
        @ApiModelProperty(value = "검색 키워드 타입(0: 제목, 1: 내용)", example = "0")
        private Integer keywordTp;
        @ApiModelProperty(value = "검색 키워드")
        private String keywordText;
    }

    @Data
    public static class updateCommReq {
        @ApiModelProperty(value = "게시글 제목")
        private String title;

        @ApiModelProperty(value = "시작 등록일 8자리 YYYYMMDD" , required = true)
        @Size(min = 8, max = 8, message = "시작 등록일 8자리를 입력해주세요.")
        private String startRegDate;

        @ApiModelProperty(value = "게시글 타입", example = "전체,장비,공지사항,음식")
        private Integer code;

        @ApiModelProperty(value = "노출 여부", example = "게시 여부(0:비공개,1:게시) ")
        private Integer status;

        @ApiModelProperty(value = "게시글 ")
        private String content;

        @ApiModelProperty(value = "검색 키워드 타입(0: 제목)", example = "0")
        private Integer keywordTp;

        @ApiModelProperty(value = "상품 이미지파일")
        @JsonIgnore
        private List<MultipartFile> files;

        @ApiModelProperty(value = "상품 이미지 리스트")
        private List<Long> uploadImgIds;

        @ApiModelProperty(value = "이미지 삭제 리스트")
        private List<Long> deleteImgIds;


    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res {
        @ApiModelProperty(value = "게시글 번호", example = "0")
        private Long communityId;

        @ApiModelProperty(value = "게시글 타입코드")
        private Integer code;

        @ApiModelProperty(value = "게시글 타입명")
        private CodeDto.CodeRes codeName;

        @ApiModelProperty(value = "게시글 제목")
        private String title;

        @ApiModelProperty(value = "게시글내용")
        private String content;

        @ApiModelProperty(value = "위치")
        private String location;

        @ApiModelProperty(value = "노출 여부")
        private Integer status;

        @ApiModelProperty(value = "좋아요 수")
        private Integer likeCnt;

        @ApiModelProperty(value = "작성일")
        private LocalDateTime createdDate;

        @ApiModelProperty(value = "에디터 업로드 이미지 리스트")
        private Set<CommImgDto.ProductImgDto> commImgs;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResList {
        @ApiModelProperty(value = "게시글 번호", example = "0")
        private Long communityId;

        @ApiModelProperty(value = "게시글 타입코드")
        private Integer code;

        @ApiModelProperty(value = "게시글 타입명")
        private CodeDto.CodeRes codeName;

        @ApiModelProperty(value = "게시글 제목")
        private String title;

        @ApiModelProperty(value = "노출 여부")
        private Integer status;

        @ApiModelProperty(value = "작성일")
        private LocalDateTime createdDate;

    }

}

package com.commerce.song.domain.dto;

import com.camping.common.domain.dto.PageDto;
import com.commerce.song.domain.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDto {
    private String id;
    private String resourceNm;
    private String httpMethod;
    private int seq;
    private String resourceTp;
    private String roleName;
    private Set<Role> roleSet;

    @Data
    public static class ReqList extends PageDto {
        @ApiModelProperty(value = "리소스 검색 타입 0: 자원명, 1: id")
        private String keywordTp;

        @ApiModelProperty(value = "키워드")
        private String keyword;

        @ApiModelProperty(value = "method")
        private String httpMethod;

        @ApiModelProperty(value = "리소스 타입")
        private String resourceTp;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResList {
        @ApiModelProperty(value = "resource id")
        private String id;
        @ApiModelProperty(value = "리소스명")
        private String resourceNm;
        @ApiModelProperty(value = "method")
        private String httpMethod;
        @ApiModelProperty(value = "순서", example = "0")
        private Integer seq;
        @ApiModelProperty(value = "리소스 타입")
        private String resourceTp;
        @ApiModelProperty(value = "생성일")
        private String createdDate;
        @ApiModelProperty(value = "생성자 id", example = "0")
        private Long createdBy;
        @ApiModelProperty(value = "수정일")
        private String lastModifiedDate;
        @ApiModelProperty(value = "수정자 id", example = "0")
        private Long lastModifiedBy;
    }

    @Data
    public static class CreateResourceReq {
        @ApiModelProperty(value = "자원명", required = true)
        @NotBlank(message = "자원명을 입력해주세요.")
        private String resourceNm;
        @ApiModelProperty(value = "method(GET, POST, PUT, PATCH, DELETE)", required = true)
        @NotBlank(message = "http method를 입력해주세요.")
        private String httpMethod;
        @ApiModelProperty(value = "리소스 타입(url, method)", required = true)
        @NotBlank(message = "리소스 타입을 입력해주세요.")
        private String resourceTp;
        @ApiModelProperty(value = "순서(기본값 99)", example = "0")
        private Integer seq = 99;
    }
    @Data
    public static class UpdateResourceReq {
        @ApiModelProperty(value = "자원명", required = true)
        @NotBlank(message = "자원명을 입력해주세요.")
        private String resourceNm;
        @ApiModelProperty(value = "method(GET, POST, PUT, PATCH, DELETE)", required = true)
        @NotBlank(message = "http method를 입력해주세요.")
        private String httpMethod;
        @ApiModelProperty(value = "리소스 타입(url, method)", required = true)
        @NotBlank(message = "리소스 타입을 입력해주세요.")
        private String resourceTp;
        @ApiModelProperty(value = "순서(기본값 99)", example = "0")
        private Integer seq = 99;
    }

    @Data
    public static class RoleResourceRes {
        @ApiModelProperty(value = "자원 번호", example = "0")
        private Long id;

    }
}

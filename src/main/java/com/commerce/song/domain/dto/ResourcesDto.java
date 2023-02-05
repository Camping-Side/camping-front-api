package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDto {
    private String id;
    private String resourceName;
    private String httpMethod;
    private int orderNum;
    private String resourceType;
    private String roleName;
    private Set<Role> roleSet;

    @Data
    public static class ReqList extends PageDto {
        @ApiModelProperty(value = "리소스명")
        private String resourceName;

        @ApiModelProperty(value = "method")
        private String httpMethod;

        @ApiModelProperty(value = "resourceType")
        private String resourceType;

        @ApiModelProperty(value = "Role id")
        private String roleId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResList {
        @ApiModelProperty(value = "resource id")
        private String id;
        @ApiModelProperty(value = "리소스명")
        private String resourceName;
        @ApiModelProperty(value = "method")
        private String httpMethod;
        @ApiModelProperty(value = "순서", example = "0")
        private Integer orderNum;
        @ApiModelProperty(value = "리소스 타입")
        private String resourceType;
        @ApiModelProperty(value = "생성일")
        private String createdDate;
        @ApiModelProperty(value = "생성자 id", example = "0")
        private Long createdBy;
        @ApiModelProperty(value = "수정일")
        private String lastModifiedDate;
        @ApiModelProperty(value = "수정자 id", example = "0")
        private Long lastModifiedBy;
    }
}

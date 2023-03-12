package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @ApiModelProperty(value = "권한 번호")
    private Long id;
    @ApiModelProperty(value = "권한 이름")
    private String roleNm;
    @ApiModelProperty(value = "권한 설명")
    private String roleDesc;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResAccountRole {
        @ApiModelProperty(value = "권한 이름")
        private String roleNm;

        public ResAccountRole(Role role) {
            this.roleNm = role.getRoleNm();
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResourceRoleRes {
        @ApiModelProperty(value = "권한 번호")
        private Long id;
        @ApiModelProperty(value = "권한 이름")
        private String roleNm;
    }

    @Data
    public static class ReqList extends PageDto {
        @ApiModelProperty(value = "권한 검색 타입 0: 권한명, 1: id")
        private String keywordTp;

        @ApiModelProperty(value = "키워드")
        private String keyword;
    }

    @Setter
    @Getter
    public static class ResList extends BaseDto{
        @ApiModelProperty(value = "권한 번호")
        private Long id;
        @ApiModelProperty(value = "권한 이름")
        private String roleNm;
        @ApiModelProperty(value = "권한 설명")
        private String roleDesc;

        public ResList(Long id, String roleNm, String roleDesc, String createdDate, Long createdBy, String lastModifiedDate, Long lastModifiedBy) {
            super(createdDate, createdBy, lastModifiedDate, lastModifiedBy);
            this.id = id;
            this.roleNm = roleNm;
            this.roleDesc = roleDesc;
        }
    }

    @Data
    public static class CreateRoleReq {
        @ApiModelProperty(value = "권한 이름", required = true)
        @NotBlank(message = "권한명을 입력해주세요.")
        private String roleNm;
        @ApiModelProperty(value = "권한 설명")
        private String roleDesc;
    }

    @Data
    public static class UpdateRoleReq {
        @ApiModelProperty(value = "권한 이름", required = true)
        @NotBlank(message = "권한명을 입력해주세요.")
        private String roleNm;
        @ApiModelProperty(value = "권한 설명")
        private String roleDesc;
    }
}

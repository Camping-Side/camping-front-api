package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @ApiModelProperty(value = "권한 번호")
    private Long id;
    @ApiModelProperty(value = "권한 이름")
    private String roleName;
    @ApiModelProperty(value = "권한 설명")
    private String roleDesc;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResAccountRole {
        @ApiModelProperty(value = "권한 이름")
        private String roleName;

        public ResAccountRole(Role role) {
            this.roleName = role.getRoleName();
        }

    }
}

package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.QRole;
import com.commerce.song.domain.entity.Role;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto extends PageVo{
    private Long id;
    private String username;
    private String password;
    private String email;
    private int age;
    private Set<Role> userRoles = new HashSet<>();

    @Data
    @ToString(callSuper=true, includeFieldNames=true)
    public static class ReqList extends PageVo{
        private String email;
        private String username;
        private List<String> roles;
    }

    @Data
    public static class ResList {
        @ApiModelProperty(value = "회원 번호")
        private Long id;
        @ApiModelProperty(value = "회원 이름")
        private String username;
        @ApiModelProperty(value = "회원 이메일")
        private String email;
        @ApiModelProperty(value = "회원 나이")
        private int age;
        @ApiModelProperty(value = "회원 권한 리스트")
        private Set<RoleDto.ResAccountRole> userRoles = new HashSet<>();

//        @QueryProjection
//        public ResList(Long id, String username, String email, int age, Set<Role> userRoles) {
//            this.id = id;
//            this.username = username;
//            this.email = email;
//            this.age = age;
//            this.userRoles = userRoles;
//        }

        public ResList(Account account){
            this.id = account.getId();
            this.username = account.getUsername();
            this.email = account.getEmail();
            this.age = account.getAge();
            this.userRoles = account.getUserRoles().stream()
                .map(RoleDto.ResAccountRole::new)
                .collect(Collectors.toSet());
        }
    }

}

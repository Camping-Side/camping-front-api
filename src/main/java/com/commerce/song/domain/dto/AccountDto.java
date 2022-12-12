package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.Role;
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
public class AccountDto extends PageDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String birth;
    private Set<Role> userRoles = new HashSet<>();

    @Data
    @ToString(callSuper=true, includeFieldNames=true)
    public static class ReqList extends PageDto {
        private String email;
        private String username;
        private List<String> roles = new ArrayList<>();
    }

    @Data
    public static class ResList {
        @ApiModelProperty(value = "회원 번호")
        private Long id;
        @ApiModelProperty(value = "회원 이름")
        private String username;
        @ApiModelProperty(value = "회원 이메일")
        private String email;
        @ApiModelProperty(value = "회원 휴대폰번호")
        private String phone;
        @ApiModelProperty(value = "회원 생년월일")
        private String birth;
        @ApiModelProperty(value = "회원 권한 리스트")
        private Set<RoleDto.ResAccountRole> userRoles = new HashSet<>();

        public ResList(Account account){
            this.id = account.getId();
            this.username = account.getUsername();
            this.email = account.getEmail();
            this.birth = account.getBirth();
            this.phone = account.getPhone();
            this.userRoles = account.getUserRoles().stream()
                .map(RoleDto.ResAccountRole::new)
                .collect(Collectors.toSet());
        }
    }

    @Data
    public static class Res {
        @ApiModelProperty(value = "회원 번호")
        private Long id;
        @ApiModelProperty(value = "회원 이름")
        private String username;
        @ApiModelProperty(value = "회원 휴대폰번호")
        private String phone;
        @ApiModelProperty(value = "회원 이메일")
        private String email;
        @ApiModelProperty(value = "회원 생년월일")
        private String birth;
        @ApiModelProperty(value = "회원 권한 리스트")
        private Set<RoleDto.ResAccountRole> userRoles = new HashSet<>();

        public Res(Account account) {
            this.id = account.getId();
            this.email = account.getEmail();
            this.username = account.getUsername();
            this.birth = account.getBirth();
            this.userRoles = account.getUserRoles().stream()
                    .map(RoleDto.ResAccountRole::new)
                    .collect(Collectors.toSet());
        }
    }

    @Data
    public static class LoginReq {
        @ApiModelProperty(value = "회원 이메일")
        private String email;
        @ApiModelProperty(value = "회원 비밀번호")
        private String password;
    }

    @Data
    public static class CheckEmailDupReq {
        @ApiModelProperty(value = "중복체크 이메일")
        private String email;
    }

    @Data
    public static class CheckEmailDupRes {
        @ApiModelProperty(value = "이메일 중복여부")
        private Boolean isDup;
        public CheckEmailDupRes(Boolean isDup) {
            this.isDup = isDup;
        }
    }
    @Data
    public static class CheckPhoneDupReq {
        @ApiModelProperty(value = "중복체크 휴대폰번호")
        private String phone;
    }

    @Data
    public static class CheckPhoneDupRes {
        @ApiModelProperty(value = "휴대폰번호 중복여부")
        private Boolean isDup;
        public CheckPhoneDupRes(Boolean isDup) {
            this.isDup = isDup;
        }
    }

    @Data
    public static class FindEmailReq {
        @ApiModelProperty(value = "회원 이름")
        private String username;
        @ApiModelProperty(value = "회원 휴대폰번호")
        private String phone;
    }

    @Data
    public static class FindEmailRes {
        @ApiModelProperty(value = "회원 이메일")
        private String email;
        public FindEmailRes(Account account) {
            this.email = account.getEmail();
        }
    }

}

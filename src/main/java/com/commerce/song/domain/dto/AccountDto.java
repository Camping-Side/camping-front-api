package com.commerce.song.domain.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private int age;
    private List<String> roles;

    @Getter
    @Setter
    public static class ReqList {
        private String email;
        private String username;
        private List<String> roles;
    }
    @Getter
    @Setter
    public static class ResList {
        private String id;
        private String username;
        private String password;
        private String email;
        private int age;
        private List<String> roles;
    }

}

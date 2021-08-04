package com.commerce.song.util;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum HttpCode {
    SUCCESS(HttpStatus.OK, "처리가 완료되었습니다.")
    ;

    private HttpStatus code;
    private String message;

    HttpCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {return this.message;}

    public static String getMessage(HttpStatus code) {
        for(HttpCode item : HttpCode.values()) {
            if(code == item.getCode()) {
                return item.getMessage();
            }
        }
        return "서버 에러가 발생했습니다.(Can't find Code Messege)";
    }

    public HttpStatus getCode() { return this.code; }
}

package com.commerce.song.exception;


public class SecurityContextAvailbleException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public SecurityContextAvailbleException(String message) {
        super(message);
    }

    public SecurityContextAvailbleException() {
        super("인증 토큰을 이용할 수 없습니다.");
    }

}

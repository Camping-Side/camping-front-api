package com.commerce.song.exception;


public class JwtNotAvailbleException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public JwtNotAvailbleException(String message) {
        super(message);
    }

    public JwtNotAvailbleException() {
        super("인증 토큰을 이용할 수 없습니다.");
    }

}

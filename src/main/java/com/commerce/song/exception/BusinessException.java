package com.commerce.song.exception;


public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {
        super("서버에 문제가 발생했습니다. 관리자에게 문의바랍니다.");
    }

}

package com.commerce.song.exception;


import com.commerce.song.util.enums.rescode.AwsCode;

public class AwsUploadException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final AwsCode code;

    public AwsUploadException(AwsCode code) {
        super(code.getMsg());
        this.code = code;
    }

    public AwsUploadException() {
        super(AwsCode.AWS_FILE_UPLOAD_FAILED.getMsg());
        code = AwsCode.AWS_FILE_UPLOAD_FAILED;
    }

    public AwsCode getCode() {
        return code;
    }
}

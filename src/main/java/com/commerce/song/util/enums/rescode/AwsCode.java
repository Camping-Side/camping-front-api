package com.commerce.song.util.enums.rescode;

public enum AwsCode implements BaseCode {
    AWS_FILE_UPLOAD_SUCCESS("AS000", "파일 업로드에 성공했습니다."),
    AWS_FILE_DELETE_SUCCESS("AS001", "파일 삭제에 성공했습니다."),

    AWS_FILE_UPLOAD_FAILED("AF000", "파일 업로드에 실패했습니다."),
    AWS_FILE_DELETE_FAILED("AF001", "파일 삭제에 실패했습니다."),
    AWS_FILE_FOUND_FAILED("AF002", "존재하지 않는 파일입니다."),
    AWS_ACCESS_LOCAL_FAILED("AF099", "로컬에서는 AWS 연결이 안됩니다."),

    //    100 번대는 이미지 관련
    AWS_IMAGE_TYPE_FAILED("AF100", "올바른 이미지 타입이 아닙니다."),
    ;

    private String code;
    private String msg;

    AwsCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() { return this.code; }
    public String getMsg() { return this.msg; }
}

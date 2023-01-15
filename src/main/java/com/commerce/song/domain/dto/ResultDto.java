package com.commerce.song.domain.dto;

import com.commerce.song.util.HttpCode;
import com.commerce.song.util.rescode.BaseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
@ApiModel
public class ResultDto<T> {
    @ApiModelProperty(value = "결과 코드")
    private String statusCode;
    @ApiModelProperty(value = "결과 메시지")
    private String resultMsg;
    @ApiModelProperty(value = "결과 데이터")
    private T resultData;

    public ResultDto(final String code, final String resultMsg) {
        this.statusCode = code;
        this.resultMsg = resultMsg;
        this.resultData = null;
    }

    public static<T> ResultDto<T> res() {
        return res("200", "처리가 완료되었습니다.", null);
    }

    public static<T> ResultDto<T> res(final BaseCode code) {
        return res(code.getCode(), code.getMsg(), null);
    }

    public static<T> ResultDto<T> res(final T t) {
        return res("200", "처리가 완료되었습니다.", t);
    }

    public static<T> ResultDto<T> res(BaseCode code, final T t) {
        return res(code.getCode(), code.getMsg() , t);
    }

    public static<T> ResultDto<T> res(final BaseCode statusCode, final String customMsg) {
        return res(statusCode.getCode(), customMsg, null);
    }

    public static<T> ResultDto<T> res(final String code, final String msg, final T t) {
        return ResultDto.<T>builder()
                .resultData(t)
                .statusCode(code)
                .resultMsg(msg)
                .build();
    }
}

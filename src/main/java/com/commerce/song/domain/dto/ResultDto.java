package com.commerce.song.domain.dto;

import com.commerce.song.util.HttpCode;
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
    private Integer statusCode;
    @ApiModelProperty(value = "결과 메시지")
    private String resultMsg;
    @ApiModelProperty(value = "결과 데이터")
    private T resultData;

    public ResultDto(final HttpStatus statusCode, final String resultMsg) {
        this.statusCode = statusCode.value();
        this.resultMsg = resultMsg;
        this.resultData = null;
    }

    public static<T> ResultDto<T> res() {
        return res(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMessage(), null);
    }

    public static<T> ResultDto<T> res(final HttpStatus statusCode) {
        return res(statusCode, HttpCode.getMessage(statusCode), null);
    }

    public static<T> ResultDto<T> res(final T t) {
        return res(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMessage(), t);
    }

    public static<T> ResultDto<T> res(final HttpStatus statusCode, final T t) {
        return res(statusCode, HttpCode.getMessage(statusCode), t);
    }

    public static<T> ResultDto<T> res(final HttpStatus statusCode, final String resultMsg) {
        return res(statusCode, resultMsg, null);
    }

    public static<T> ResultDto<T> res(final HttpStatus statusCode, final String resultMsg, final T t) {
        return ResultDto.<T>builder()
                .resultData(t)
                .statusCode(statusCode.value())
                .resultMsg(resultMsg)
                .build();
    }
}

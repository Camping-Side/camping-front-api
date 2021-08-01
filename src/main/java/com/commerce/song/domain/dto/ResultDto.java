package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@ApiModel
public class ResultDto<T> {
    @ApiModelProperty(value = "결과 코드")
    private int statusCode;
    @ApiModelProperty(value = "결과 메시지")
    private String resultMsg;
    @ApiModelProperty(value = "결과 데이터")
    private T resultData;

    public ResultDto(final int statusCode, final String resultMsg) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = null;
    }

    public static<T> ResultDto<T> res(final int statusCode, final String resultMsg) {
        return res(statusCode, resultMsg, null);
    }

    public static<T> ResultDto<T> res(final int statusCode, final String resultMsg, final T t) {
        return ResultDto.<T>builder()
                .resultData(t)
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .build();
    }
}

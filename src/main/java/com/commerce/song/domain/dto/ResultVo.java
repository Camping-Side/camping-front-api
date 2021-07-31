package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultVo {
    @ApiModelProperty(value = "결과 데이터")
    Object resultData;
    @ApiModelProperty(value = "결과 메시지")
    Object resultMsg;

    public ResultVo(Object resultData, Object resultMsg) {
        this.resultData = resultData;
        this.resultMsg = resultMsg;
    }

    public ResultVo(Object resultData) {
        this.resultData = resultData;
        this.resultMsg = "Ok";
    }
}

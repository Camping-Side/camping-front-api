package com.commerce.song.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultVo {
    Object resultData;
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

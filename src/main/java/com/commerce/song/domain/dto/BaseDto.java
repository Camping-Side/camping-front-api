package com.commerce.song.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BaseDto {
    @ApiModelProperty(value = "생성일 YYYY-MM-DD")
    private String createdDate;
    @ApiModelProperty(value = "생성 id")
    private Long createdBy;
    @ApiModelProperty(value = "수정일 YYYY-MM-DD")
    private String lastModifiedDate;
    @ApiModelProperty(value = "수정 id")
    private Long lastModifiedBy;
}

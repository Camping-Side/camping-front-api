package com.commerce.song.domain.dto;

import com.camping.common.domain.dto.BaseDto;
import com.camping.common.domain.dto.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
public class VenderDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRes {
        @ApiModelProperty(value = "벤더 id", example = "0")
        private Long vdrId;
        @ApiModelProperty(value = "벤더명")
        private String vdrNm;
    }

    @Data
    public static class ReqList extends PageDto {
        @ApiModelProperty(value = "벤더사 상태(0: 신청, 1: 심사중, 2: 승인, 3: 거절)", example = "0")
        private Integer vdrSts;
    }

    @Setter
    @Getter
    public static class ResList extends BaseDto {
        @ApiModelProperty(value = "벤더사id")
        private Long vdrId;
        @ApiModelProperty(value = "벤더사명")
        private String vdrNm;
        @ApiModelProperty(value = "벤더사 상태(0: 신청, 1: 심사중, 2: 승인, 3: 거절)", example = "0")
        private Integer vdrSts;

        public ResList(Long vdrId, String vdrNm, Integer vdrSts, String createdDate, Long createdBy, String lastModifiedDate, Long lastModifiedBy) {
            super(createdDate, createdBy, lastModifiedDate, lastModifiedBy);
            this.vdrId = vdrId;
            this.vdrNm = vdrNm;
            this.vdrSts = vdrSts;
        }
    }
}

package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.SpecialPlan;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SpecialPlanDto {
    private Long planId;
    private String planNm;
    private Long categoryId;
    private String desc;
    private LocalDateTime start_Date;
    private LocalDateTime end_Date;
    private String show_Yn;
    private Long sts;
    private List<SpecialPlanDto> planList = new ArrayList<>();

    private List<Rel_ProductsDto> relProductsDtoList = new ArrayList<>();

    public SpecialPlanDto(Long planId, String planNm, Long categoryId, String desc, LocalDateTime start_Date, LocalDateTime end_Date, String show_Yn, Long sts, List<SpecialPlanDto> planList, List<Rel_ProductsDto> relProductsDtoList) {
        this.planId = planId;
        this.planNm = planNm;
        this.categoryId = categoryId;
        this.desc = desc;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.show_Yn = show_Yn;
        this.sts = sts;
        this.planList = planList;
        this.relProductsDtoList = relProductsDtoList;
    }

    @Data
    public static class ResSpecialPlan extends PageDto{
        @ApiModelProperty(value ="특별전고유번호",example = "0")
        private Long planId;
        @ApiModelProperty(value="특별전제목")
        private String planNm;
        @ApiModelProperty(value = "특별전 카테고리번호", example = "")
        private Long categoryId;
        @ApiModelProperty(value = "특별전 설명")
        private String desc;
        @ApiModelProperty(value = "특별전 시작일자")
        private LocalDateTime start_Date;
        @ApiModelProperty(value="특별전 종료일자")
        private LocalDateTime end_Date;
        @ApiModelProperty(value = "특별전 노출여부", example = "Y, N")
        private String show_Yn;
        @ApiModelProperty(value ="특별전 상태값", example = "0 : 대기, 1: 진행중, 2: 종료")
        private Long sts;
        @ApiModelProperty(value = "특별전 리스트")
        private List<SpecialPlanDto> planList = new ArrayList<>();
    }

}

package com.commerce.song.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EventDto {
    @ApiModelProperty(value = "이벤트번호", example = "0")
    private Long event_Id;
    @ApiModelProperty(value = "이벤트명")
    private String event_Name;
    @ApiModelProperty(value = "이벤트 시작일")
    private LocalDateTime start_Date;
    @ApiModelProperty(value = "이벤트 종료일")
    private LocalDateTime end_Date;
    @ApiModelProperty(value = "콘텐츠")
    private String contents;
    @ApiModelProperty(value = "이벤트 노출여부", example = "노출여부(Y, N)")
    private String show_Yn;
    @ApiModelProperty(value = "이벤트 상태값", example = "이벤트 상태값(0:대기,1:진행중,2:진행종료)")
    private Long sts;
    @ApiModelProperty(value = "이벤트 배너url")
    private String event_Banner;
    @ApiModelProperty(value = "이벤트 리스트")
    private List<EventDto> eventList = new ArrayList<>();
    @QueryProjection
    public EventDto(Long event_Id, String event_Name, LocalDateTime start_Date, LocalDateTime end_Date, String contents, String show_Yn, Long sts, String event_Banner, List<EventDto> eventList) {
        this.event_Id = event_Id;
        this.event_Name = event_Name;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.contents = contents;
        this.show_Yn = show_Yn;
        this.sts = sts;
        this.event_Banner = event_Banner;
        this.eventList = eventList;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResEvent {
        private Long event_Id;
        private String event_Name;
        private LocalDateTime start_Date;
        private LocalDateTime end_Date;
        private String contents;
        private String show_Yn;
        private Long sts;
        private String event_Banner;
        private List<EventDto> eventList = new ArrayList<>();
     }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchEventList extends PageDto{
        private Long event_Id;
        private String event_Name;
        private LocalDateTime start_Date;
        private LocalDateTime end_Date;
        private String contents;
        private String show_Yn;
        private Long sts;
        private String event_Banner;
        private List<EventDto> eventList = new ArrayList<>();
    }
}

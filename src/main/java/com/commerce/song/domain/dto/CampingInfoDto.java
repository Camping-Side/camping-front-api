package com.commerce.song.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampingInfoDto {
    private Long id;
    private String title;
    private String commend;
    private Integer recomType;
    private Integer thumbnailType;
    private String content;
    private String location;
    private Integer lowPrc;
    private Long locationId;
    private Long campType;
    private String bookUrl;
    private Integer sts;
    private String grade;
    private Integer likeCnt;
    private Set<Long> keywordIds;
}

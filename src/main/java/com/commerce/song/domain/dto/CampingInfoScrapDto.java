package com.commerce.song.domain.dto;

import lombok.*;

import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampingInfoScrapDto {
    private Long scrapId;
    private Long campingInfo;
}

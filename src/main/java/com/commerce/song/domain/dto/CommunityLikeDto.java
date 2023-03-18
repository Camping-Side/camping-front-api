package com.commerce.song.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityLikeDto {
    private Long id;
    private Long accountId;
    private Long communityId;

}

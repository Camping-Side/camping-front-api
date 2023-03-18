package com.commerce.song.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private Long communityId;
    private String content;
    private Long parentId;
    private Integer status;
}
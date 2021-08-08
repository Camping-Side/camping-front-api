package com.commerce.song.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PageDto {
    Integer page;
    Integer size;
}

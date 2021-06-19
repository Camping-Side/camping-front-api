package com.commerce.song.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PageVo {
    Integer page;
    Integer size;
}

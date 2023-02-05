package com.commerce.song.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class PageDto {
    @NotNull(message = "page가 null입니다.")
    Integer page;
    @NotNull(message = "size가 null입니다.")
    Integer size;
}

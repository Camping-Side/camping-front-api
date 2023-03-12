package com.commerce.song.controller;

import com.commerce.song.domain.dto.EventDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "이벤트 페이지 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @ApiOperation(value ="이벤트 리스트 조회", notes="이벤트 리스트 조회")
    @GetMapping
    public ResultDto<Page<EventDto.searchEventList>> searchEventListAll(@Validated @ModelAttribute EventDto.searchEventList search) {
        Page<EventDto.searchEventList> list = eventService.findAll(search);
        return ResultDto.res(list);
    }
}

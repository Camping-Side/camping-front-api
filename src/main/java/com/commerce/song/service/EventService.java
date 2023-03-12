package com.commerce.song.service;

import com.commerce.song.domain.dto.EventDto;
import org.springframework.data.domain.Page;

public interface EventService {

    Page<EventDto.searchEventList> findAll(EventDto.searchEventList search);
}

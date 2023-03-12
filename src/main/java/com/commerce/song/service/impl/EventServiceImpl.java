package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.EventDto;
import com.commerce.song.service.EventService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    @Override
    public Page<EventDto.searchEventList> findAll(EventDto.searchEventList search) {

        ArrayList<EventDto.searchEventList> list = new ArrayList<>();





        return null;
    }
}

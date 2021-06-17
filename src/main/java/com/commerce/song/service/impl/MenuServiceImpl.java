package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    @Override
    public List<MenuDto> getAllMenu() {
        return null;
    }
}

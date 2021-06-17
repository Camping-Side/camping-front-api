package com.commerce.song.controller;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuController {
    private final MenuService menuService;
    @GetMapping
    public List<MenuDto> getAllMenu() {
        return null;
    }
}

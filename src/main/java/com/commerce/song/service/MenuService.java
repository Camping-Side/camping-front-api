package com.commerce.song.service;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.domain.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MenuService {
    Page<Menu> getAllMenu(PageRequest pageRequest);
    Menu save(Menu menu, Long parentId);
    void delete(Long id);
    void update(Long id, Menu menu, Long parentId);
    Menu findById(Long id);
}

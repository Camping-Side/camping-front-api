package com.commerce.song.repository.custom;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.domain.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuRepositoryCustom {
    Page<Menu> findAllToDtoPage(Pageable pageable);
    List<Menu> findAllToDto();
}

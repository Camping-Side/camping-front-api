package com.commerce.song.controller;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.domain.dto.PageVo;
import com.commerce.song.domain.dto.ResultVo;
import com.commerce.song.domain.entity.Menu;
import com.commerce.song.service.MenuService;
import com.commerce.song.util.CustomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<ResultVo> findAll(@ModelAttribute PageVo pageVo) {
        PageRequest pageRequest = PageRequest.of(pageVo.getPage(), pageVo.getSize());
        Page<Menu> allMenu = menuService.getAllMenu(pageRequest);
        return new ResponseEntity<>(new ResultVo(allMenu), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResultVo> createMenu(@Valid @RequestBody MenuDto menuDto) {
        Menu savedMenu = menuService.save(CustomUtil.convertClass(menuDto, Menu.class), menuDto.getParentId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMenu.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(new ResultVo(savedMenu.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultVo> deleteMenu(@PathVariable Long id) {
        menuService.delete(id);
        return new ResponseEntity<>(new ResultVo(null, "delete ok"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultVo> updateMenu(@PathVariable Long id,
                                               @RequestBody @Valid MenuDto menuDto) {
        menuService.update(id, CustomUtil.convertClass(menuDto, Menu.class), menuDto.getParentId());
        return new ResponseEntity<>(new ResultVo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultVo> findById(@PathVariable Long id) {
        Menu findMenu = menuService.findById(id);
        return new ResponseEntity<>(new ResultVo(CustomUtil.convertClass(findMenu, Menu.class)), HttpStatus.OK);
    }
}

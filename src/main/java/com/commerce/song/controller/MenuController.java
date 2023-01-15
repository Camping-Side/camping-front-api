package com.commerce.song.controller;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.entity.Menu;
import com.commerce.song.service.MenuService;
import com.commerce.song.util.CustomUtil;
import com.commerce.song.util.HttpCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuController {
    private final MenuService menuService;

//    @GetMapping
//    public ResponseEntity<ResultVo> findAllPage(@ModelAttribute PageVo pageVo) {
//        PageRequest pageRequest = PageRequest.of(pageVo.getPage(), pageVo.getSize());
//        Page<Menu> allMenu = menuService.getAllMenu(pageRequest);
//        return new ResponseEntity<>(new ResultVo(allMenu), HttpStatus.OK);
//    }

    @GetMapping
    public ResultDto<List<Menu>> findAll() {
        List<Menu> allMenu = menuService.getAllMenu();
        return ResultDto.res(allMenu);
    }

    @PostMapping
    public ResponseEntity<ResultDto<Long>> createMenu(@Valid @RequestBody MenuDto menuDto) {
        Menu savedMenu = menuService.save(CustomUtil.convertClass(menuDto, Menu.class), menuDto.getParentId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMenu.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(ResultDto.res(savedMenu.getId()));
    }

    @DeleteMapping("/{id}")
    public ResultDto deleteMenu(@PathVariable Long id) {
        menuService.delete(id);
        return ResultDto.res();
    }

    @PutMapping("/{id}")
    public ResultDto updateMenu(@PathVariable Long id,
                                               @RequestBody @Valid MenuDto menuDto) {
        menuService.update(id, CustomUtil.convertClass(menuDto, Menu.class), menuDto.getParentId());
        return ResultDto.res();
    }

    @GetMapping("/{id}")
    public ResultDto<Menu> findById(@PathVariable Long id) {
        Menu findMenu = menuService.findById(id);
        return ResultDto.res(CustomUtil.convertClass(findMenu, Menu.class));
    }
}

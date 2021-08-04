package com.commerce.song.controller;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.domain.dto.PageVo;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.entity.Menu;
import com.commerce.song.service.MenuService;
import com.commerce.song.util.CustomUtil;
import com.commerce.song.util.HttpCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.transform.Result;
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
    public ResponseEntity<ResultDto<List<Menu>>> findAll() {
        List<Menu> allMenu = menuService.getAllMenu();
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, HttpCode.getMessage(HttpStatus.OK), allMenu));
    }

    @PostMapping
    public ResponseEntity<ResultDto<Long>> createMenu(@Valid @RequestBody MenuDto menuDto) {
        Menu savedMenu = menuService.save(CustomUtil.convertClass(menuDto, Menu.class), menuDto.getParentId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMenu.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(ResultDto.res(HttpStatus.OK, HttpCode.getMessage(HttpStatus.OK), savedMenu.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultDto> deleteMenu(@PathVariable Long id) {
        menuService.delete(id);
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, HttpCode.getMessage(HttpStatus.OK)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDto> updateMenu(@PathVariable Long id,
                                               @RequestBody @Valid MenuDto menuDto) {
        menuService.update(id, CustomUtil.convertClass(menuDto, Menu.class), menuDto.getParentId());
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, HttpCode.getMessage(HttpStatus.OK)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDto<Menu>> findById(@PathVariable Long id) {
        Menu findMenu = menuService.findById(id);
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, HttpCode.getMessage(HttpStatus.OK), CustomUtil.convertClass(findMenu, Menu.class)));
    }
}

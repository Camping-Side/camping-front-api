package com.commerce.song.controller;

import com.commerce.song.domain.dto.ResourcesDto;
import com.commerce.song.domain.dto.RoleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(tags = "자원 rest api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resources")
public class ResourceConroller {

    @ApiOperation(value = "자원 리스트 조회")
    @GetMapping
    public ResultDto<Page<ResourcesDto.ResList>> findAll(@Validated @ModelAttribute ResourcesDto.ReqList req) {
        return ResultDto.res();
    }

    @ApiOperation(value = "자원 추가")
    @PostMapping
    public ResponseEntity<ResultDto<Long>> createResource(@Validated @RequestBody ResourcesDto.CreateResourceReq req) {
        Long resourceId = 1L;

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resourceId)
                .toUri();
        return ResponseEntity.created(location)
                .body(ResultDto.res(resourceId));
    }
    @ApiOperation(value = "자원 수정")
    @PutMapping(value = "/{id}")
    public ResultDto updateResource(@PathVariable Long id,
            @Validated @RequestBody ResourcesDto.UpdateResourceReq req) {

        return ResultDto.res();
    }
    @ApiOperation(value = "자원 삭제")
    @DeleteMapping("/{id}")
    public ResultDto deleteById(@PathVariable Long id) {
        return ResultDto.res();
    }

    @ApiOperation(value = "자원 매핑 권한 조회")
    @GetMapping("/{id}/roles")
    public ResultDto<List<RoleDto.ResourceRoleRes>> findResourceRoleById(@PathVariable Long id) {
        return ResultDto.res();
    }
}

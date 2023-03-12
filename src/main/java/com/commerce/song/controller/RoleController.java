package com.commerce.song.controller;

import com.commerce.song.domain.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Api(tags = "권한 rest api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    @ApiOperation(value = "권한 리스트 조회")
    @GetMapping
    public ResultDto<Page<RoleDto.ResList>> findAll(@Validated @ModelAttribute RoleDto.ReqList req) {
        return ResultDto.res();
    }

    @ApiOperation(value = "권한 추가")
    @PostMapping
    public ResponseEntity<ResultDto<Long>> createRole(@Validated @RequestBody RoleDto.CreateRoleReq req) {
        Long roleId = 1L;

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(roleId)
                .toUri();
        return ResponseEntity.created(location)
                .body(ResultDto.res(roleId));
    }
    @ApiOperation(value = "권한 수정")
    @PutMapping(value = "/{id}")
    public ResultDto updateRole(@PathVariable Long id,
                                    @Validated @RequestBody RoleDto.UpdateRoleReq req) {

        return ResultDto.res();
    }
    @ApiOperation(value = "권한 삭제")
    @DeleteMapping("/{id}")
    public ResultDto deleteById(@PathVariable Long id) {
        return ResultDto.res();
    }

    @ApiOperation(value = "권한 매핑 회원 조회")
    @GetMapping("/{id}/accounts")
    public ResultDto<Page<AccountDto.RoleAccountRes>> findRoleAccountById(
            @PathVariable Long id,
            @Validated @RequestBody PageDto pageDto) {
        return ResultDto.res();
    }

    @ApiOperation(value = "권한 매핑 회원 추가")
    @PostMapping("/{roleId}/accounts/{accountId}")
    public ResultDto<Long> createRoleAccount(@PathVariable(value = "roleId") Long roleId,
                                             @PathVariable(value = "accountId") Long accountId) {
        return ResultDto.res();
    }

}

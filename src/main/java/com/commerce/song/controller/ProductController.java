package com.commerce.song.controller;

import com.commerce.song.domain.dto.ProductDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "상품 rest api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value="상품 리스트 조회", notes = "상품 리스트 조회")
    @GetMapping
    public ResultDto<Page<ProductDto.ResList>> findAll(@Validated @RequestBody ProductDto.ReqList req) {
        Page<ProductDto.ResList> all = productService.findAll(req);
        return ResultDto.res(all);
    }
}

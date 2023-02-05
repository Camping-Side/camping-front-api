package com.commerce.song.controller;

import com.commerce.song.domain.dto.ProductDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(tags = "상품 rest api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value="상품 리스트 조회", notes = "상품 리스트 조회")
    @GetMapping
    public ResultDto<Page<ProductDto.ResList>> findAll(@Validated @ModelAttribute ProductDto.ReqList req) {
        Page<ProductDto.ResList> all = productService.findAll(req);
        return ResultDto.res(all);
    }

    @ApiOperation(value = "상품 추가")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResultDto<Long>> createProduct(@Validated @ModelAttribute ProductDto.createProductReq reqDto) {
        Long productId = productService.createProduct(reqDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productId)
                .toUri();
        return ResponseEntity.created(location)
                .body(ResultDto.res(productId));
    }

    @ApiOperation(value = "상품 조회")
    @GetMapping("/{id}")
    public ResultDto<ProductDto.Res> findById(@PathVariable Long id) {
        return productService.findById(id);
    }


    @ApiOperation(value = "상품 삭제")
    @DeleteMapping("/{id}")
    public ResultDto deleteById(@PathVariable Long id) {
        return ResultDto.res();
    }

    @ApiOperation(value = "상품 수정")
    @PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResultDto updateById(@PathVariable Long id, @Validated @ModelAttribute ProductDto.updateProductReq reqDto) {
        return ResultDto.res();
    }
}

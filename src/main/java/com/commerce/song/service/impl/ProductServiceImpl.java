package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AccountDto;
import com.commerce.song.domain.dto.ProductDto;
import com.commerce.song.repository.ProductRepository;
import com.commerce.song.service.ProductService;
import com.commerce.song.util.CustomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<ProductDto.ResList> findAll(ProductDto.ReqList requestDto) {
        return productRepository.findAllToDtoPage(CustomUtil.convertPageVo(requestDto), requestDto);
    }
}

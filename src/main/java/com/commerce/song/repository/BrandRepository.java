package com.commerce.song.repository;

import com.commerce.song.domain.entity.Brand;
import com.commerce.song.repository.dsl.BrandRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long>, BrandRepositoryCustom {
    Brand findByBrandNm(String name);
}

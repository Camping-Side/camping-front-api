package com.commerce.song.repository;

import com.commerce.song.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByBrandNm(String name);
}

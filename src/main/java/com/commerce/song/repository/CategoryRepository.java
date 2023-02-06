package com.commerce.song.repository;

import com.commerce.song.domain.entity.Brand;
import com.commerce.song.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryNm(String name);
}

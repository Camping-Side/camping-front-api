package com.commerce.song.repository;

import com.commerce.song.domain.entity.Account;
import com.commerce.song.domain.entity.Product;
import com.commerce.song.repository.dsl.AccountRepositoryCustom;
import com.commerce.song.repository.dsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Product findByName(String name);
}

package com.commerce.song.repository;


import com.commerce.song.domain.entity.Product;
import com.commerce.song.repository.dsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Product findByName(String name);

    @Query("select distinct p from Product p join fetch p.brand join fetch p.category join fetch p.vender left join fetch p.productOptions left join fetch p.commImgs where p.productId = :id")
    Optional<Product> findProductEagerById(Long id);
}

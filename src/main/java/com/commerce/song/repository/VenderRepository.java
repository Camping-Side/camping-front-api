package com.commerce.song.repository;

import com.commerce.song.domain.entity.Brand;
import com.commerce.song.domain.entity.Vender;
import com.commerce.song.repository.dsl.VenderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenderRepository extends JpaRepository<Vender, Long>, VenderRepositoryCustom {
    Vender findByVdrNm(String name);
}

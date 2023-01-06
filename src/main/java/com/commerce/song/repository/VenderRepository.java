package com.commerce.song.repository;

import com.commerce.song.domain.entity.Brand;
import com.commerce.song.domain.entity.Vender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenderRepository extends JpaRepository<Vender, Long> {
    Vender findByVdrNm(String name);
}

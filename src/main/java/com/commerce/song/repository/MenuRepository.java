package com.commerce.song.repository;

import com.commerce.song.domain.entity.Menu;
import com.commerce.song.repository.dsl.MenuRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {

}

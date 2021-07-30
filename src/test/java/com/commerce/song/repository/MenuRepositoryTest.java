package com.commerce.song.repository;

import com.commerce.song.domain.entity.Menu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MenuRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    public void 메뉴전체조회() {
        Menu menu1 = new Menu("메뉴1", "아이콘1", "경로1", 1, 1, "Y");
        Menu menu2 = new Menu("메뉴2", "아이콘2", "경로2", 1, 2, "Y");

        em.persist(menu1);
        em.persist(menu2);
        menu1.addChildMenu(menu2);


        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Menu> allToDto = menuRepository.findAllToDtoPage(pageRequest);

        assertThat(allToDto.getContent().get(0).getMenuName()).isEqualTo("메뉴1");
        assertThat(allToDto.getContent().get(0).getChild().get(0).getMenuName()).isEqualTo("메뉴2");
    }
}

package com.commerce.song.repository.dsl;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.domain.dto.QMenuDto;
import com.commerce.song.domain.entity.Menu;
import com.commerce.song.domain.entity.QMenu;
import com.commerce.song.repository.custom.MenuRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.commerce.song.domain.entity.QMenu.*;


@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Page<Menu> findAllToDto(Pageable pageable) {
        QMenu parent = new QMenu("parent");
        QMenu child = new QMenu("child");
        QueryResults<Menu> result = query
                .select(parent)
                .from(parent)
                .distinct()
                .leftJoin(parent.child, child)
                .fetchJoin()
                .where(
                        parent.parent().isNull()
                )
                .orderBy(parent.menuSeq.asc(), child.menuSeq.asc())
                .fetchResults();


        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}

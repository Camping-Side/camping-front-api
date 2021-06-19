package com.commerce.song.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MenuDto {
    private Long id;
    private String menuName;
    private String icon;
    private String path;
    private Integer menuSeq;
    private Integer menuLevel;
    private String menuUseYn;
    private MenuDto parent;
    private Long parentId;
    private List<MenuDto> child = new ArrayList<>();

    @QueryProjection
    public MenuDto(Long id, String menuName, String icon, String path, Integer menuSeq, Integer menuLevel, String menuUseYn, MenuDto parent, List<MenuDto> child) {
        this.id = id;
        this.menuName = menuName;
        this.icon = icon;
        this.path = path;
        this.menuSeq = menuSeq;
        this.menuLevel = menuLevel;
        this.menuUseYn = menuUseYn;
        this.parent = parent;
        this.child = child;
    }
}

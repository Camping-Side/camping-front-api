package com.commerce.song.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Menu {
    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "icon")
    private String icon;

    @Column(name = "path")
    private String path;

    @Column(name = "menu_seq")
    private Integer menuSeq;

    @Column(name = "menu_level")
    private Integer menuLevel;

    @Column(name = "menu_use_yn")
    private String menuUseYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Menu parent;

    @OneToMany(mappedBy = "parent")
    private List<Menu> child = new ArrayList<>();

    // == 연관관계 메서드 ==
    public void addChildMenu(Menu child) {
        this.child.add(child);
        child.setParent(this);
    }
    private void setParent(Menu menu) {
        this.parent = menu;
    }
}

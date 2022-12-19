package com.commerce.song.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "menu_id")
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
    public void setParent(Menu menu) {
        this.parent = menu;
    }

    public Menu(String menuName, String icon, String path, Integer menuSeq, Integer menuLevel, String menuUseYn) {
        this.menuName = menuName;
        this.icon = icon;
        this.path = path;
        this.menuSeq = menuSeq;
        this.menuLevel = menuLevel;
        this.menuUseYn = menuUseYn;
    }

    public void updateMenu(Menu menu) {
        this.menuName = menu.menuName;
        this.menuUseYn = menu.menuUseYn;
        this.icon = menu.icon;
        this.menuSeq = menu.menuSeq;
        this.menuLevel = menu.menuSeq;
        this.path = menu.path;
    }
}

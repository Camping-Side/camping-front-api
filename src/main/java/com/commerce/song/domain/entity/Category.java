package com.commerce.song.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "category")
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", length = 50, nullable = false)
    private String categoryName;

    @Column(name = "icon", length = 50)
    private String icon;

    @Column
    @ColumnDefault("0")
    private Integer seq;

    @Column
    @ColumnDefault("0")
    private Integer level;

    @Column(name = "use_yn", length = 2)
    @ColumnDefault("'Y'")
    private String useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}

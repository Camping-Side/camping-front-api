package com.commerce.song.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "brand")
public class Brand extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", length = 50, nullable = false)
    private String brandName;

    @Column(length = 100)
    private String intro;

    @Column(name = "office_tel", length = 12)
    private Integer officeTel;

    @Column(name = "brand_url")
    private String brandUrl;

    @Column(length = 2)
    @ColumnDefault("'Y'")
    private String useYn;

    @Column(name = "brand_img")
    private String brandImg;
}

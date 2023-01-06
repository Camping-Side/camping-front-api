package com.commerce.song.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand")
public class Brand extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

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

    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();
}

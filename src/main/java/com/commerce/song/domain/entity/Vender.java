package com.commerce.song.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name ="vender")
public class Vender extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vdr_id")
    private Long vdrId;

    @Column(name = "vdr_nm", length = 50, nullable = false)
    private String vdrNm;

    @Column(name = "vdr_sts", length = 2, nullable = false)
    @ColumnDefault("0")
    private String vdrSts;

    @OneToMany(mappedBy = "vender")
    private List<Product> products = new ArrayList<>();

}

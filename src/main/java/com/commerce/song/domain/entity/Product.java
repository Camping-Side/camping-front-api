package com.commerce.song.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(length = 120)
    private String name;

    @Column(name = "tax_tp", length = 2, nullable = false)
    @ColumnDefault("0")
    private Integer taxTp;

    @Column(name = "prd_tp", length = 2, nullable = false)
    @ColumnDefault("0")
    private Integer prdTp;

    @Column(name = "prd_sts", length = 2, nullable = false)
    @ColumnDefault("0")
    private Integer prdSts;

    @Column(name = "product_desc", columnDefinition = "LONGTEXT")
    private String productDesc;

    @Column(name = "supply_prc", nullable = false, length = 15)
    private Integer supplyPrc;

    @Column(name = "sale_prc", nullable = false, length = 15)
    private Integer salePrc;

    @Column(name = "prd_prc", nullable = false, length = 15)
    private Integer prdPrc;

    @Column(name = "total_cnt", nullable = false, length = 11)
    private Integer totalCnt;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vdr_id")
    private Vender vender;

}

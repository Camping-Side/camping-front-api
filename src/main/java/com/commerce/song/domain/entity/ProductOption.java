package com.commerce.song.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_option")
public class ProductOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opt_id")
    private Long optId;

    @Column(name = "opt_value1", length = 150)
    private String optValue1;

    @Column(name = "opt_value2", length = 150)
    private String optValue2;

    @Column(name = "opt_value3", length = 150)
    private String optValue3;

    @Column(name = "opt_supply_prc", length = 10, nullable = false)
    private Integer optSupplyPrc;

    @Column(name = "opt_prc", length = 10, nullable = false)
    private Integer optPrc;

    @Column(name = "opt_cnt", length = 10, nullable = false)
    private Integer optCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }
}

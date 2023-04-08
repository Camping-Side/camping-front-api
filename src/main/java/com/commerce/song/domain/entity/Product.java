package com.commerce.song.domain.entity;

import com.commerce.song.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

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

    @Column(name = "supply_prc", nullable = false, length = 10)
    private Integer supplyPrc;

    @Column(name = "sale_prc", nullable = false, length = 10)
    private Integer salePrc;

    @Column(name = "prd_prc", nullable = false, length = 10)
    private Integer prdPrc;

    @Column(name = "total_cnt", nullable = false, length = 10)
    private Integer totalCnt;

    @Column(name = "delry_cd", nullable = false, length = 10)
    private String delryCd;

    @Column(name = "delry_tp", nullable = false, length = 2)
    private String delryTp;

    @Column(name = "delry_base_start_prc")
    private Integer delryBaseStartPrc;

    @Column(name = "delry_prc", nullable = false)
    private Integer delryPrc;

    @Column(name = "delry_side_prc", nullable = false)
    private Integer delrySidePrc;

    @Column(name = "delry_jeju_prc", nullable = false)
    private Integer delryJejuPrc;

    @Column(name = "delry_out_addr", nullable = false)
    private String delryOutAddr;

    @Column(name = "delry_out_addr2", nullable = false)
    private String delryOutAddr2;

    @Column(name = "delry_ref_addr", nullable = false)
    private String delryRefAddr;

    @Column(name = "delry_ref_addr2", nullable = false)
    private String delryRefAddr2;

    @Column(name = "opt_tp", nullable = false)
    private Integer optTp;

    @Column(name = "opt_title1", length = 100)
    private String optTitle1;

    @Column(name = "opt_title2", length = 100)
    private String optTitle2;

    @Column(name = "opt_title3", length = 100)
    private String optTitle3;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductOption> productOptions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_id")
    private Set<CommImg> commImgs;

    public void of(Category category, Brand brand, Vender vender) {
        this.category = category;
        this.brand = brand;
        this.vender = vender;
    }

    public void setSaleDate(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATE_NON_DASH);

        if(StringUtils.hasText(startDate)) this.startDate = LocalDate.parse(startDate, formatter).atStartOfDay();

        if(StringUtils.hasText(endDate)) this.endDate = LocalDate.parse(endDate, formatter).atStartOfDay();
    }


}

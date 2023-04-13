package com.commerce.song.domain.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "camping_info")
public class CampingInfo extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campinfo_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "commend")
    private String commend;

    @Column(name = "recom_type", length = 12)
    private Integer recomType;

    @Column(name = "thumbnail_type", length = 12)
    private Integer thumbnailType;

    @Lob
    @Column(name = "content" , columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "location")
    private String location;

    @Column(name = "lowest_prc")
    private Integer lowPrc;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "camp_type")
    private Long campType;

    @Column(name = "book_url")
    private String bookUrl;

    @Column(name = "status", length = 12)
    @ColumnDefault("0")
    private Integer sts;

    @Column(name = "grade", length = 12)
    private String grade;

    @Column(name = "like_cnt", length = 12)
    private Integer likeCnt;

    @Column(name="disp_start_date")
    private LocalDateTime dispStartDate;

    @Column(name="disp_end_date")
    private LocalDateTime dispEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "campinfo_id")
    private Set<CampingInfoScrap> campInfoScraps;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "campinfo_id")
    private Set<CampingInfoLike> campInfoLikes;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "campinfo_id")
    private Set<Keyword> keywords;
}

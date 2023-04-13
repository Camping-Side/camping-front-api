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
@Table(name = "keyword")
public class Keyword extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordId;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campinfo_id", nullable = false)
    private CampingInfo campingInfo;
}

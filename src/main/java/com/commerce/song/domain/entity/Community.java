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
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "community")
public class Community extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @Column(name = "code")
    private Integer code; //community_tp

    @Column
    private String content;

    @Column
    private String title;

    @Column(name = "location", length = 250)
    private String location;

    @Column(name = "status", length = 12)
    @ColumnDefault("0")
    private Integer sts; //sts 네이밍 룰 따르기

    @Column(name = "like_cnt", length = 20)
    private Integer likeCnt;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
    private Set<CommunityLike> communityLikes;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

}

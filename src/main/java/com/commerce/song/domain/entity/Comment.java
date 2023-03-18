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
@Table(name = "comment")
public class Comment extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column
    private String content;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "status", length = 12)
    @ColumnDefault("0")
    private Integer sts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

}

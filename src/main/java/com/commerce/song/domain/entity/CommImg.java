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
@Table(name = "comm_img")
public class CommImg  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

    @Column(name = "ref_id")
    private Long refId;

    @Column(name = "type")
    private Integer type;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "img_path")
    private String imgPath;

}

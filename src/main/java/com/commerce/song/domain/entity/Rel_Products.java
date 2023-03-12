package com.commerce.song.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rel_products")
public class Rel_Products extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rel_products_id")
    private Long relId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "rel_event_id", nullable = false)
    private Long eventId;

    @Column(name = "rel_event_category", nullable = false)
    private String relCategory;




}

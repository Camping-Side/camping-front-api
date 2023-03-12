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
@Table(name = "special_plan")
public class SpecialPlan extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_plan_id")
    private Long planId;

    @Column(name = "special_plan_name")
    private String planNm;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "description")
    private String desc;

    @Column(name = "start_date")
    private LocalDateTime start_Date;

    @Column(name = "end_date")
    private LocalDateTime end_Date;

    @Column(name = "show_yn")
    @ColumnDefault("'N'")
    private String show_Yn;

    @Column(name = "status")
    @ColumnDefault("0")
    private Long sts;

}

package com.commerce.song.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Many;
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
@Table(name = "equipment")
public class Equipment extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long equipmentId;

    @OneToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(name = "equipment_name")
    private String equipmentName;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}

package com.commerce.song.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "resources")
@Data
@ToString(exclude = {"roleSet"})
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resources extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long id;

    @Column(name = "resource_nm")
    private String resourceNm;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "seq")
    private int seq;

    @Column(name = "resource_tp")
    private String resourceTp;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_resources", joinColumns = {
            @JoinColumn(name = "resource_id")
    }, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet = new HashSet<>();

}

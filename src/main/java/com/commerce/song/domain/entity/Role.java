package com.commerce.song.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Builder
@ToString(exclude = {"users","resourcesSet"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Role extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    @OrderBy("orderNum desc")
    private Set<Resources> resourcesSet = new LinkedHashSet<>();
}

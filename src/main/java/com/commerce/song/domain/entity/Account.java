package com.commerce.song.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"userRoles"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Account extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private int age;

    @JsonIgnore
    @Column
    private String password;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id")}
    )
    private Set<Role> userRoles = new HashSet<>();


}

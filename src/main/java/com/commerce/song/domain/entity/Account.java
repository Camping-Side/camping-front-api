package com.commerce.song.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "account")
@ToString(exclude = {"userRoles"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Account extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column(length = 8)
    private String birth;

    @Column(length = 12)
    private String phone;

    @JsonIgnore
    @Column
    private String password;

    @JsonIgnore
    @Column(name = "activated", columnDefinition="tinyint(1) default 1")
    @Builder.Default
    private boolean activated = true;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id")}
    )
    private Set<Role> userRoles = new HashSet<>();


}

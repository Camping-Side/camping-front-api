package com.commerce.song.domain.entity;

import com.commerce.song.domain.dto.AccountDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Column(length = 30)
    private String username;

    @Column(length = 30)
    private String nickname;

    @Column(unique = true, nullable = false)
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

    @Column(name = "market_agree", columnDefinition="tinyint(1) default 0")
    @Builder.Default
    private boolean marketAgree = false;

    @Column(name="leave_date")
    private LocalDateTime leaveDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id")}
    )
    private Set<Role> userRoles = new HashSet<>();


    // 회원 탈퇴 / 정지 (배치로 5년 지난 회원 실제 삭제처리)
    public void leave() {
        this.activated = false;
        this.leaveDate = LocalDateTime.now();
    }

    public void updateByAdmin(AccountDto.UpdateAccountReq req) {
        this.username = req.getUsername();
        this.activated = req.isActivated();
        this.email = req.getEmail();
        this.phone = req.getPhone();
        this.nickname = req.getNickname();
        this.birth = req.getBirth();
    }


}

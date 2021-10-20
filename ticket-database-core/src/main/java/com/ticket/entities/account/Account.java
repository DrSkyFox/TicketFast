package com.ticket.entities.account;


import com.ticket.entities.account.ref.AccountRoleRef;
import com.ticket.enums.AccountStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;



@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false, length = 64, unique = true)
    private String login;

    @Column(name = "email", nullable = false, length = 64, unique = true)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 1024)
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable =false)
    private AccountStatus status;

    @Builder.Default
    @Column(name = "enabled",  nullable = false)
    private Boolean enabled = true;

    @Builder.Default
    @Column(name = "accountNonExpired")
    private Boolean accountNonExpired = true;

    @Builder.Default
    @Column(name = "accountNonLocked")
    private Boolean accountNonLocked = true;

    @Builder.Default
    @Column(name = "credentialsNonExpired")
    private Boolean credentialsNonExpired = true;

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private UserInfo userInfo;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserPurchasingInfo userPurchasingInfo;

    @OneToMany(mappedBy = "account")
    private List<AccountRoleRef> accountRoleRef;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", enabled=" + enabled +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                '}';
    }





}

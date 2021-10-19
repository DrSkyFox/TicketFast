package com.ticket.entities.account;


import com.ticket.entities.organization.Organization;
import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 32)
    private String firstName;

    @Column(name = "second_name", length = 32)
    private String secondName;

    @Column(name ="third_name", length = 32)
    private String thirdName;

    @Column(name ="phone", length = 15)
    private String phone;

    @Column(name ="isOrganized")
    private Boolean isOrganized;

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Account account;


}

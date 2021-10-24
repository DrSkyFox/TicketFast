package com.ticket.entities.organization.reference;

import com.ticket.entities.account.Account;
import com.ticket.entities.organization.Organization;
import com.ticket.entities.organization.Staff;
import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff_account_reg")
public class OrganizationStaffAccountReg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name ="staff_id")
    private Staff staff;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name ="account_id")
    private Account account;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name ="organization_id")
    private Organization organization;

    @Builder.Default
    @Column(name ="isActive", nullable = false)
    private Boolean isActive = true;

}

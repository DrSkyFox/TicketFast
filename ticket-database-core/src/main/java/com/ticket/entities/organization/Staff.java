package com.ticket.entities.organization;


import com.ticket.entities.organization.reference.OrganizationStaffAccountReg;
import com.ticket.enums.StaffType;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="staff_type")
    private StaffType staffType;


    @Builder.Default
    @Column(name = "isActive", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<OrganizationStaffAccountReg> organizationStaffAccountRegs;

}

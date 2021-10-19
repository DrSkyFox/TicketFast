package com.ticket.entities.organization;


import com.ticket.entities.organization.reference.RoleStaffsRef;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_staff")
public class RoleStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(name ="name")
    private String name;

    @OneToOne(mappedBy = "roleStaff", cascade = CascadeType.ALL)
    private Privileges privileges;

    @OneToMany(mappedBy = "roleStaff", cascade = CascadeType.ALL)
    private List<RoleStaffsRef> roleStaffsRefSet;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive = true;




}

package com.ticket.entities.special.reference;


import com.ticket.entities.organization.Organization;
import com.ticket.entities.special.AddressBook;
import com.ticket.entities.templates.PlaceTemplate;
import com.ticket.enums.AddressType;
import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address_reg_ref")
public class AddressRegRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="addressBook_id", nullable = false)
    private AddressBook addressBook;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name ="organization_id", nullable = false)
    private Organization organization;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name ="placetemplate_id", nullable = false)
    private PlaceTemplate placeTemplate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;

    @Builder.Default
    @Column(name="isActive", nullable = false)
    private Boolean isActive = true;



}

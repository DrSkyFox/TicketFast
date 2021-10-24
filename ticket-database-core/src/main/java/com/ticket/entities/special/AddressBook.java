package com.ticket.entities.special;

import com.ticket.entities.organization.Organization;
import com.ticket.entities.special.reference.AddressRegRef;
import com.ticket.enums.AddressType;
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
@Table(name = "address_book")
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city", nullable = false, length = 128)
    private String city;

    @Column(name = "address", nullable = false, length = 512)
    private String address;

    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;

    @Column(name = "corps", nullable = false)
    private String corps;

    @Enumerated(EnumType.ORDINAL)
    private AddressType addressType;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    private Set<AddressRegRef> addressRegRef;

    @Builder.Default
    @Column(name = "isActive")
    private Boolean isActive = true;

}

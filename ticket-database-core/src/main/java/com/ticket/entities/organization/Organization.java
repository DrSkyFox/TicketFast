package com.ticket.entities.organization;


import com.ticket.entities.account.Account;
import com.ticket.entities.special.reference.AddressRegRef;
import com.ticket.entities.templates.PlaceTemplate;
import com.ticket.entities.templates.TicketTemplate;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "inn")
    private Integer iNN;

    @Builder.Default
    @Column(name = "isIndividual", nullable = false)
    private Boolean isIndividual = false;

    //ogrn; ip - 10 , not ip - 13
    @Column(name = "ogrn", length = 13)
    private Integer oGRN;

    @Column(name = "kpp", length = 9)
    private Integer KPP;

    //okpo; ip - 10 ; not ip - 8
    @Column(name = "okpo", length = 10)
    private Integer oKPO;


    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<PlaceTemplate> places;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<TicketTemplate> ticketTemplates;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<AddressRegRef> addressRegRefs;

}

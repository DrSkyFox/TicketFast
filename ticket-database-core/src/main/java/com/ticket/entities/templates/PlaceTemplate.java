package com.ticket.entities.templates;


import com.ticket.entities.organization.Organization;
import com.ticket.entities.special.RentInfo;
import com.ticket.entities.templates.reference.DiaryPlaceRefTemplate;
import com.ticket.entities.templates.reference.TicketPlaceRefTemplate;
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
@Table(name = "place_template")
public class PlaceTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "shortDescription", length = 128)
    private String shortDescription;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "city", nullable = false, length = 128)
    private String city;

    @Column(name = "address", nullable = false, length = 512)
    private String address;

    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;

    @Column(name = "corps", nullable = false)
    private String corps;

    @Column(name = "isRent", nullable = false)
    private Boolean isRent;

    @Column(name = "count_place")
    private Long countPlace;

    @Column(name = "hasArea", nullable = false)
    private Boolean hasArea;

    @Column(name = "deleteAfterRentExpire", nullable = false)
    private Boolean deleteAfterRentExpire;

    @OneToOne(fetch = FetchType.LAZY)
    private RentInfo rentInfo;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "place_template", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AreaTemplate> areas;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "placeTemplate")
    private Set<DiaryPlaceRefTemplate> refDiaryTemp;

    @OneToMany(mappedBy = "placeTemplate")
    private Set<TicketPlaceRefTemplate> ticketPlaceRefTemplates;




}

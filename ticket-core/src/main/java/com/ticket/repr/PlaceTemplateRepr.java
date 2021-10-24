package com.ticket.repr;

import com.ticket.entities.organization.Organization;
import com.ticket.entities.special.RentInfo;
import com.ticket.entities.templates.AreaTemplate;
import com.ticket.entities.templates.reference.DiaryPlaceRefTemplate;
import com.ticket.entities.templates.reference.TicketPlaceRefTemplate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class PlaceTemplateRepr {

    private Long id;

    private String name;

    private String shortDescription;

    private String description;

    private String city;


    private String address;

    private String houseNumber;

    private String corps;


    private Boolean isRent;

    private Long countPlace;

    private Boolean hasArea;

    private Boolean deleteAfterRentExpire;

    private RentInfo rentInfo;

    private Double latitude;

    private Double longitude;

    private Organization organization;

    private List<AreaTemplate> areas;

    private Boolean isActive = true;

    private Set<DiaryPlaceRefTemplate> refDiaryTemp;

    private Set<TicketPlaceRefTemplate> ticketPlaceRefTemplates;





}

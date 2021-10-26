package com.ticket.controllers;

import com.ticket.entities.account.Account;
import com.ticket.entities.organization.Organization;
import com.ticket.model.ResponseModel;
import com.ticket.repr.OrganizationRepr;
import com.ticket.service.IOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("api/v1/organization/")
public class OrganizationController {

    private static AtomicLong idRequest = new AtomicLong(0l);

    private final IOrganizationService service;

    @Autowired
    public OrganizationController(IOrganizationService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseModel creteOrganization(OrganizationRepr organizationRepr) {
        return null;
    }

    @GetMapping("/getOrganization")
    public OrganizationRepr getOrganization() {

    }

    @GetMapping("/getOrganizationByName")
    public OrganizationRepr getOrganizationByName(String name) {

    }


    @RequestMapping(value = "/test_create")
    public ResponseModel test() throws Exception {
        Long requestNum = idRequest.incrementAndGet();
        log.info("Test Request Num {}", requestNum);

        service.createOrganizationRepr(
                Account.builder().id(1l).build(),
                OrganizationRepr.builder().name("test" + UUID.randomUUID().toString()).isIndividual(false).build());

        return ResponseModel.builder().id(requestNum).status("Organization Created success").build();
    }




}

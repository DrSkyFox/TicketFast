package com.ticket.controllers;

import com.ticket.entities.account.Account;
import com.ticket.repr.OrganizationRepr;
import com.ticket.service.IFacadeOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/organization/")
public class OrganizationController {

    private final IFacadeOrganizationService organizationService;

    @Autowired
    public OrganizationController(IFacadeOrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/test_create")
    public void test() throws Exception {
        organizationService.createNewOrganization(Account.builder().id(1l).build(), OrganizationRepr.builder().name("test" + UUID.randomUUID().toString()).isIndividual(false).build());
    }


}

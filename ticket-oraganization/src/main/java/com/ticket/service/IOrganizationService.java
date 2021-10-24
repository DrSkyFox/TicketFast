package com.ticket.service;

import com.ticket.entities.account.Account;
import com.ticket.entities.organization.Organization;
import com.ticket.fetch.organization.OrganizationFetch;
import com.ticket.repr.OrganizationRepr;

import java.util.Set;

public interface IOrganizationService {

    OrganizationRepr createOrganizationRepr(OrganizationRepr organization);

    OrganizationRepr updateOrganizationRepr(OrganizationRepr organization);

    Organization createOrganization(Organization organization);

    Organization updateOrganization(Organization organization);

    void deleteOrganizationById( Long id);

    OrganizationRepr findOrganizationByName(String nameOrganization, Set<OrganizationFetch> fetch);


}

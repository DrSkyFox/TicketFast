package com.ticket.serviceimplement;

import com.ticket.entities.account.Account;
import com.ticket.entities.organization.Organization;
import com.ticket.fetch.organization.OrganizationFetch;
import com.ticket.repr.OrganizationRepr;
import com.ticket.service.IOrganizationService;
import com.ticket.repositories.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

@Service
public class OrganizationServiceImpl implements IOrganizationService {


    private final OrganizationRepository repository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrganizationRepr createOrganizationRepr(OrganizationRepr organization) {

        return null;
    }


    @Override
    public OrganizationRepr findOrganizationByName(String nameOrganization, Set<OrganizationFetch> fetch) {
        OrganizationRepr organization  = repository.findByName(nameOrganization).map(OrganizationRepr::new).orElse(null);
        if ((organization != null) && (fetch != null)) {

        }
        return organization;
    }

    @Override
    @Transactional
    public OrganizationRepr updateOrganizationRepr(OrganizationRepr organization) {
        return null;
    }

    @Override
    @Transactional
    public void deleteOrganizationById(Long id) {

    }

    @Override
    @Transactional
    public Organization createOrganization(Organization organization) {
        return repository.save(organization);
    }

    @Override
    @Transactional
    public Organization updateOrganization(Organization organization) {
        return null;
    }
}

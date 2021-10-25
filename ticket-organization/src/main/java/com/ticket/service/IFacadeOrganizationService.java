package com.ticket.service;



import com.ticket.entities.account.Account;
import com.ticket.entities.organization.Staff;
import com.ticket.repr.AccountRepr;
import com.ticket.repr.OrganizationRepr;

public interface IFacadeOrganizationService {

    OrganizationRepr getOrganization(Account account);

    OrganizationRepr getOrganization(Staff staff);

    OrganizationRepr createNewOrganization(Account account, OrganizationRepr organization) throws Exception;

    void deleteOrganization(Account account);

}

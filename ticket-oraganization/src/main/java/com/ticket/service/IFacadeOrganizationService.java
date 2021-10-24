package com.ticket.service;



import com.ticket.entities.account.Account;
import com.ticket.repr.AccountRepr;
import com.ticket.repr.OrganizationRepr;

public interface IFacadeOrganizationService {

    OrganizationRepr createNewOrganization(Account account, OrganizationRepr organization);

    void deleteOrganization(Account account);



}

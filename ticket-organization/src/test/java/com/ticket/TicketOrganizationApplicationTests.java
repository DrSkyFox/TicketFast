package com.ticket;

import com.ticket.entities.account.Account;
import com.ticket.repositories.organization.OrganizationRepository;
import com.ticket.repositories.organization.StaffAccountRegRepository;
import com.ticket.repositories.organization.StaffRepository;
import com.ticket.repr.OrganizationRepr;
import com.ticket.serviceimplement.OrganizationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = TicketOrganizationApplication.class)
@ExtendWith(MockitoExtension.class)
class TicketOrganizationApplicationTests {


    @Mock
    StaffRepository staffRepository;

    @Mock
    OrganizationRepository organizationRepository;

    @Mock
    StaffAccountRegRepository staffAccountRegRepository;

    @Mock
    OrganizationServiceImpl organization;

    @Mock
    StaffServiceImpl staffService;

    @InjectMocks
    FacadeOrganizationServiceImpl organizationService;



    @Test
    @Transactional
    void contextLoads() throws Exception {
        Account account = new Account();
        account.setId(1l);

        organizationService.createNewOrganization(account, OrganizationRepr.builder().name("test").build());

    }

}

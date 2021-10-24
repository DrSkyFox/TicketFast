package com.ticket.serviceimplement;

import com.ticket.entities.account.Account;
import com.ticket.entities.organization.Organization;
import com.ticket.entities.organization.Staff;
import com.ticket.entities.organization.reference.OrganizationStaffAccountReg;
import com.ticket.enums.StaffType;
import com.ticket.repositories.organization.StaffAccountRegRepository;
import com.ticket.repr.AccountRepr;
import com.ticket.repr.OrganizationRepr;

import com.ticket.service.IFacadeOrganizationService;
import com.ticket.service.IOrganizationService;
import com.ticket.service.IStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Slf4j
@Service
public class FacadeOrganizationServiceImpl implements IFacadeOrganizationService {

    private final IOrganizationService organizationService;
    private final IStaffService staffService;
    private final StaffAccountRegRepository staffAccountRegRepository;

    @Autowired
    public FacadeOrganizationServiceImpl(IOrganizationService organizationService, IStaffService staffService, StaffAccountRegRepository staffAccountRegRepository) {
        this.organizationService = organizationService;
        this.staffService = staffService;
        this.staffAccountRegRepository = staffAccountRegRepository;
    }

    @Override
    @Transactional
    public OrganizationRepr createNewOrganization(Account account, OrganizationRepr organizationRepr) {
        if (organizationService.findOrganizationByName(organizationRepr.getName(), null) == null) {
            Staff staff = staffService.addNewStaff(Staff.builder()
                    .isActive(true)
                    .staffType(StaffType.OWNER)
                    .build());
            Organization organization = organizationService.createOrganization(
                    Organization.builder()
                            .name(organizationRepr.getName())
                            .iNN(organizationRepr.getINN())
                            .isIndividual(organizationRepr.getIsIndividual())
                            .oGRN(organizationRepr.getOGRN())
                            .KPP(organizationRepr.getKPP())
                            .oKPO(organizationRepr.getOKPO())
                            .isActive(true)
                            .build()
            );
            staffAccountRegRepository.save(OrganizationStaffAccountReg.builder()
                    .organization(organization)
                    .account(account)
                    .staff(staff)
                    .isActive(true)
                    .build());
        }
        return null;
    }

    @Override
    public void deleteOrganization(Account account) {

    }
}

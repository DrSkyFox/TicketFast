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

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Optional;

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
    @Transactional(rollbackFor = Exception.class)
    public OrganizationRepr createNewOrganization(Account account, OrganizationRepr organizationRepr) throws Exception {

        log.info("Create new Organization: {} by account {}", organizationRepr.getName(), account.toString());
        OrganizationRepr organizationR = organizationService.findOrganizationByName(organizationRepr.getName(), null);
        if (organizationR == null) {

            log.info("Create Staff Owner");
            Staff staff = staffService.addNewStaff(Staff.builder()
                    .isActive(true)
                    .staffType(StaffType.OWNER)
                    .build());
            log.info("Created staff {}", staff.toString());

            if(true) {
                throw new Exception("some exception");
            }
            log.info("Create organization");
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
            log.info("Created organization: {}", organization.toString());

            log.info("Reg Account Staff Org");
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

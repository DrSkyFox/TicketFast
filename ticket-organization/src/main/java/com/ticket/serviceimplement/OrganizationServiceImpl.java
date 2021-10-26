package com.ticket.serviceimplement;

import com.ticket.entities.account.Account;
import com.ticket.entities.organization.Organization;
import com.ticket.entities.organization.Staff;
import com.ticket.entities.organization.reference.OrganizationStaffAccountReg;
import com.ticket.enums.StaffType;
import com.ticket.exceptions.OrganizationException;
import com.ticket.exceptions.OrganizationExistsException;
import com.ticket.fetch.organization.OrganizationFetch;
import com.ticket.repositories.organization.OrganizationRepository;
import com.ticket.repositories.organization.StaffAccountRegRepository;
import com.ticket.repositories.organization.StaffRepository;
import com.ticket.repr.OrganizationRepr;
import com.ticket.service.IOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class OrganizationServiceImpl implements IOrganizationService {


    private final OrganizationRepository repository;
    private final StaffRepository staffRepository;
    private final StaffAccountRegRepository staffAccountRegRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository repository, StaffRepository staffRepository, StaffAccountRegRepository staffAccountRegRepository) {
        this.repository = repository;
        this.staffRepository = staffRepository;
        this.staffAccountRegRepository = staffAccountRegRepository;
    }

    @Transactional
    @Override
    public OrganizationRepr createOrganizationRepr(Account account, OrganizationRepr organizationRepr) {
        log.info("Create new Organization: {} by account {}", organizationRepr.getName(), account.toString());


        try {

            if (repository.findByName(organizationRepr.getName()).isEmpty()) {
                throw new OrganizationExistsException("Organization with name " + organizationRepr.getName() + " already exists");
            }

            Staff staffToDB = Staff.builder()
                    .isActive(true)
                    .staffType(StaffType.OWNER)
                    .build();

            log.info("Create Staff Owner");
            Optional<Staff> staff = Optional.of(staffRepository.save(staffToDB));
            if (staff.isPresent()) {
                log.info("Created staff {}", staff.get().toString());
            }

            log.info("Create organization");
            Optional<Organization> organization = Optional.of(repository.save(
                    Organization.builder()
                            .name(organizationRepr.getName())
                            .iNN(organizationRepr.getINN())
                            .isIndividual(organizationRepr.getIsIndividual())
                            .oGRN(organizationRepr.getOGRN())
                            .KPP(organizationRepr.getKPP())
                            .oKPO(organizationRepr.getOKPO())
                            .isActive(true)
                            .build()
            ));

            if (organization.isPresent()) {
                log.info("Created organization: {}", organization.get().toString());
            }

            log.info("Reg organization owner");
            staffAccountRegRepository.save(OrganizationStaffAccountReg.builder()
                    .organization(organization.get())
                    .account(account)
                    .staff(staff.get())
                    .isActive(true)
                    .build());

            return new OrganizationRepr(organization.get());
        } catch (DataAccessException | OrganizationException e) {
            throw new OrganizationException("Cant create organization", e.getCause());
        }

    }


    @Override
    public OrganizationRepr findOrganizationByName(String nameOrganization, Set<OrganizationFetch> fetch) {
        OrganizationRepr organization = repository.findByName(nameOrganization).map(OrganizationRepr::new).orElse(null);
        return organization;
    }

    @Override
    @Transactional
    public OrganizationRepr updateOrganizationRepr(OrganizationRepr organizationRepr) {
        Optional<Organization> organization = Optional.of(repository.save(
                Organization.builder()
                        .id(organizationRepr.getId())
                        .name(organizationRepr.getName())
                        .iNN(organizationRepr.getINN())
                        .isIndividual(organizationRepr.getIsIndividual())
                        .oGRN(organizationRepr.getOGRN())
                        .KPP(organizationRepr.getKPP())
                        .oKPO(organizationRepr.getOKPO())
                        .isActive(true)
                        .build()
        ));

        organizationRepr = organization.map(OrganizationRepr::new).orElse(null);
        return organizationRepr;

    }

    @Override
    @Transactional
    public void deleteOrganizationById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else throw new OrganizationExistsException("Organization with id: " + id + "not exists");
    }


    @Override
    @Transactional
    public Organization updateOrganization(Organization organization) {
        return repository.save(organization);
    }
}

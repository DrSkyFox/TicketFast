package com.ticket.repositories.organization;

import com.ticket.entities.organization.Organization;
import com.ticket.entities.organization.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAllByOrganizationStaffAccountRegsOrganizationId(Long organizationId);

    List<Staff> findAllByOrganizationStaffAccountRegsOrganization(Organization organization);



}

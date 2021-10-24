package com.ticket.serviceimplement;

import com.ticket.entities.organization.Staff;
import com.ticket.repositories.organization.StaffRepository;
import com.ticket.repr.StaffRepr;
import com.ticket.service.IStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class StaffServiceImpl implements IStaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    @Transactional
    public StaffRepr addNewStaffRepr(StaffRepr staff) {

        return Optional.of(staffRepository.save(
                Staff.builder()
                        .isActive(staff.getIsActive())
                        .staffType(staff.getStaffType())
                        .build())
        ).map(StaffRepr::new).orElse(null);
    }

    @Override
    @Transactional
    public StaffRepr updateStaffRepr(StaffRepr staff) {
        return Optional.of(staffRepository.save(
                Staff.builder()
                        .id(staff.getId())
                        .isActive(staff.getIsActive())
                        .staffType(staff.getStaffType())
                        .build())
        ).map(StaffRepr::new).orElse(null);
    }

    @Override
    @Transactional
    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }


    @Override
    @Transactional
    public Staff addNewStaff(Staff staff) {
        log.info("Add new Staff: {}", staff.toString());
        return staffRepository.save(staff);
    }

    @Override
    @Transactional
    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}

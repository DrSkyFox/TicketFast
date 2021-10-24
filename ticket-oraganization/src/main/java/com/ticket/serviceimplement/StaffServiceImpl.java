package com.ticket.serviceimplement;

import com.ticket.entities.organization.Staff;
import com.ticket.repositories.organization.StaffRepository;
import com.ticket.repr.StaffRepr;
import com.ticket.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffServiceImpl implements IStaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffRepr addNewStaffRepr(StaffRepr staff) {
        return Optional.of(staffRepository.save(
                Staff.builder()
                        .isActive(staff.getIsActive())
                        .staffType(staff.getStaffType())
                        .build())
        ).map(StaffRepr::new).orElse(null);
    }

    @Override
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
    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }


    @Override
    public Staff addNewStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}

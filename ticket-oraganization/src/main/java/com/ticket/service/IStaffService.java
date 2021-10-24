package com.ticket.service;


import com.ticket.entities.organization.Staff;
import com.ticket.repr.StaffRepr;

public interface IStaffService {

    StaffRepr addNewStaffRepr(StaffRepr staff);
    StaffRepr updateStaffRepr(StaffRepr staff);

    void deleteStaffById(Long id);

    Staff addNewStaff(Staff staff);
    Staff updateStaff(Staff staff);

}

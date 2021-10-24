package com.ticket.repr;


import com.ticket.entities.organization.Staff;
import com.ticket.entities.organization.reference.OrganizationStaffAccountReg;
import com.ticket.enums.StaffType;
import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
public class StaffRepr {

    private Long id;


    private StaffType staffType;

    private Boolean isActive = true;

    private List<OrganizationStaffAccountReg> organizationStaffAccountRegs;

    public StaffRepr(Staff staff) {
        this.id = staff.getId();
        this.staffType = staff.getStaffType();
        this.isActive = staff.getIsActive();
    }
}

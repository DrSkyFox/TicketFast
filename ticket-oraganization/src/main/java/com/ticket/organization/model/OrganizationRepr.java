package com.ticket.organization.model;


import com.ticket.entities.account.Account;
import com.ticket.entities.account.UserInfo;
import com.ticket.entities.organization.RoleStaff;
import com.ticket.entities.templates.TicketTemplate;
import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRepr {

    private Long id;

    private String name;

    private Integer iNN;

    private Boolean isIndividual;

    private Integer oGRN;

    private Integer kPP;

    private Integer oKPO;

    private List<AddressBookRepr> addresses;

    private Boolean isActive;

    private List<PlaceTemplateRepr> places;

    private List<TicketTemplateRepr> ticketTemplates;

    private Account account;

    private List<RoleStaffRepr> roleStaffs;

}

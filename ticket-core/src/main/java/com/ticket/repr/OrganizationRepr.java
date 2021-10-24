package com.ticket.repr;


import com.ticket.entities.organization.Organization;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRepr {

    private Long id;

    @NotEmpty
    private String name;

    private Integer iNN;

    @NotNull
    private Boolean isIndividual;

    private Integer oGRN;

    private Integer kPP;

    private Integer oKPO;

    private List<AddressBookRepr> addresses;

    private Boolean isActive;

    private List<PlaceTemplateRepr> places;

    private List<TicketTemplateRepr> ticketTemplates;

    private StaffRepr staffRepr;


    public OrganizationRepr(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.iNN = organization.getINN();
        this.isIndividual = organization.getIsIndividual();
        this.oGRN = organization.getOGRN();
        this.kPP = organization.getKPP();
        this.oKPO = organization.getOKPO();
        this.isActive = organization.getIsActive();
    }
}

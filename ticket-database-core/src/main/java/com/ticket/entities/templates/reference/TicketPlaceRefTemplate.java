package com.ticket.entities.templates.reference;


import com.ticket.entities.templates.PlaceTemplate;
import com.ticket.entities.templates.TicketTemplate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket_place_reference")
public class TicketPlaceRefTemplate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_temp_id")
    private TicketTemplate ticketTemplate;

    @ManyToOne
    @JoinColumn(name = "place_temp_id")
    private PlaceTemplate placeTemplate;

    @Column(name = "register")
    private LocalDateTime register = LocalDateTime.now();

    @Builder.Default
    @Column(name = "isActive")
    private Boolean isActive = true;


}





package com.ticket.entities.templates;

import com.ticket.entities.diarypack.reference.DiaryAreaDiaryPlaceReg;
import com.ticket.entities.templates.reference.TicketAoPlaceRefTemplate;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "area_templates")
public class AreaTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code", length = 32, nullable = false, unique = true)
    private String code;

    @Column(name="name", length = 64, nullable = false)
    private String name;

    @Column(name = "max_count_places", nullable = false)
    private Long maxCountPlaces;

    @Column(name = "isActive")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_template", referencedColumnName = "id")
    private PlaceTemplate place_template;

    @OneToMany(mappedBy = "areaTemplate")
    private Set<TicketAoPlaceRefTemplate> arenaTicketReference;





}

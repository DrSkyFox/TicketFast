package com.ticket.entities.diarypack;


import com.ticket.entities.diarypack.reference.DiaryAreaDiaryPlaceReg;
import com.ticket.entities.templates.PlaceTemplate;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diary_place")
public class DiaryPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "diaryPlace")
    private Set<DiaryAreaDiaryPlaceReg> diaryAreaDiaryPlaceRegSet;

    @OneToOne
    @JoinColumn(name = "olace_template_id", referencedColumnName = "id")
    private PlaceTemplate placeTemplate;


    @Column(name ="maxCountTicket", nullable = false)
    private Integer maxCountTicket;

    @Builder.Default
    @Column(name ="hasArea", nullable = false)
    private Boolean hasArea =true;

    @Builder.Default
    @Column(name = "soldCountTicket")
    private Integer soldCountTicket = 0;

    @Builder.Default
    @Column(name = "isActive")
    private Boolean isActive = true;


}

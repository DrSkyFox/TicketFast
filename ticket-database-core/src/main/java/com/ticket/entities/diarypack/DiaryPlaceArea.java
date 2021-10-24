package com.ticket.entities.diarypack;


import com.ticket.entities.diarypack.reference.DiaryAreaDiaryPlaceReg;
import com.ticket.entities.templates.AreaTemplate;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diary_place_area")
public class DiaryPlaceArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToMany(mappedBy = "diaryPlaceArea")
    private Set<DiaryAreaDiaryPlaceReg> diaryAreaPlaceRegs;

    @OneToOne
    @JoinColumn(name = "area_template_id", referencedColumnName = "id")
    private AreaTemplate templateArea;

    @Column(name = "max_count_place")
    private Long maxCountAreaPlaces;

    @Column(name = "sold_area_place")
    private Long soldAreaPlace;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;



}

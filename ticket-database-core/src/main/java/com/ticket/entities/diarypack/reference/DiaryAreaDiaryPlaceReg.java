package com.ticket.entities.diarypack.reference;


import com.ticket.entities.diarypack.DiaryPlace;
import com.ticket.entities.diarypack.DiaryPlaceArea;
import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diary_area_place_reg")
public class DiaryAreaDiaryPlaceReg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diary_place_id")
    private DiaryPlace diaryPlace;


    @ManyToOne
    @JoinColumn(name = "diary_place_area_id")
    private DiaryPlaceArea  diaryPlaceArea;


    @Builder.Default
    @Column(name ="isActive", nullable = false)
    private Boolean isActive = true;


}

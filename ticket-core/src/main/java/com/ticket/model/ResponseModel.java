package com.ticket.model;


import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    private Long id;
    private String status;


}

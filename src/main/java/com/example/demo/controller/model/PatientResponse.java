package com.example.demo.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientResponse {

    private String firstName;
    private String secondName;
    private Long countVisits;

    public PatientResponse(String firstName, String secondName, Long countVisits) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.countVisits = countVisits;
    }

}

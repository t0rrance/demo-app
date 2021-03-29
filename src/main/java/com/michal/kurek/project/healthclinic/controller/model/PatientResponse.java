package com.michal.kurek.project.healthclinic.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientResponse {

    private String firstName;
    private String secondName;
    private Long countVisits;

}

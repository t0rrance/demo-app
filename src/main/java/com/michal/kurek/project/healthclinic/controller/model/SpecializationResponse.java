package com.michal.kurek.project.healthclinic.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecializationResponse {

    private String visit;
    private Long numberOfVisits;

}

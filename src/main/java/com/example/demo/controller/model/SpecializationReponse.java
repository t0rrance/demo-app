package com.example.demo.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecializationReponse {

    private String visit;
    private Long numberOfVisits;

    public SpecializationReponse(String visit, Long numberOfVisits) {
        this.visit = visit;
        this.numberOfVisits = numberOfVisits;
    }
}

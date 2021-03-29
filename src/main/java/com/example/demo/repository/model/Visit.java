package com.example.demo.repository.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "visits")
public class Visit {
    @Id
    private Long visitId;

    @ManyToOne
    private Practitioner practitioner;

    @ManyToOne
    private Patient patient;

    public Visit() {
    }

    public Visit(Long visitId, Practitioner practitioner, Patient patient) {
        this.visitId = visitId;
        this.practitioner = practitioner;
        this.patient = patient;
    }

}

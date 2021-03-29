package com.example.demo.repository.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "patient2practitioners")
public class Patient2Practitioner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Practitioner practitioner;

    @OneToOne
    private Patient patient;

    public Patient2Practitioner() {
    }

    public Patient2Practitioner(Long id, Practitioner practitioner, Patient patient) {
        this.id = id;
        this.practitioner = practitioner;
        this.patient = patient;
    }

}

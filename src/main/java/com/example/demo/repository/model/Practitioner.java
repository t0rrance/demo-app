package com.example.demo.repository.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name = "practitioners")
public class Practitioner {
    @Id
    private Long practitionerId;
    private String specialization;

    public Practitioner() {
    }

    public Practitioner(Long practitionerId, String specialization) {
        this.practitionerId = practitionerId;
        this.specialization = specialization;
    }

}
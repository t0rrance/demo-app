package com.michal.kurek.project.healthclinic.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "visits")
public class Visit {

    @Id
    private Long visitId;

    @ManyToOne
    private Practitioner practitioner;

    @ManyToOne
    private Patient patient;

}

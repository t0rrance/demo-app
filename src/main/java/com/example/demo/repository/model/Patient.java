package com.example.demo.repository.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Builder
@Table(name = "patients")
public class Patient {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private Date createdAt;

    public Patient() {

    }

    public Patient(Long id, String firstName, String lastName, String city, Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.createdAt = createdAt;
    }

}

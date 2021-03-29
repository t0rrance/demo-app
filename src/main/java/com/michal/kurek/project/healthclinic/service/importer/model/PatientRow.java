package com.michal.kurek.project.healthclinic.service.importer.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class PatientRow {

    @CsvBindByName(column = "patientId")
    private Long patientId;

    @CsvBindByName(column = "firstName")
    private String firstName;

    @CsvBindByName(column = "lastName")
    private String lastName;

    @CsvBindByName(column = "city")
    private String city;

    @CsvBindByName(column = "createdAt")
    private String createdAt;

}

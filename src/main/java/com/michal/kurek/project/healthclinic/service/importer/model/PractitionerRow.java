package com.michal.kurek.project.healthclinic.service.importer.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class PractitionerRow {

    @CsvBindByName(column = "practitionerId")
    private Long practitionerId;

    @CsvBindByName(column = "specialization")
    private String specialization;

}

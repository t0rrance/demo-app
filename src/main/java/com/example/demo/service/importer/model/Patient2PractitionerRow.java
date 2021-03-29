package com.example.demo.service.importer.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Patient2PractitionerRow {

    @CsvBindByName(column = "patientId")
    private Long patientId;

    @CsvBindByName(column = "practitionerId")
    private Long practitionerId;

}


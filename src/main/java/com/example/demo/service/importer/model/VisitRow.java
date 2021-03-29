package com.example.demo.service.importer.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class VisitRow {

    @CsvBindByName(column = "visitId")
    private Long visitId;

    @CsvBindByName(column = "practitionerId")
    private Long practitionerId;

    @CsvBindByName(column = "patientId")
    private Long patientId;

}


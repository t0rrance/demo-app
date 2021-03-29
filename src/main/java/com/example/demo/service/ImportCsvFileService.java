package com.example.demo.service;

import com.example.demo.service.importer.Patient2PractitionerImporter;
import com.example.demo.service.importer.PatientImporter;
import com.example.demo.service.importer.PractitionerImporter;
import com.example.demo.service.importer.VisitImporter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;


@Service
public class ImportCsvFileService {

    private final PatientImporter patientImporter;
    private final PractitionerImporter practitionerImporter;
    private final VisitImporter visitImporter;
    private final Patient2PractitionerImporter patient2PractitionerImporter;

    public ImportCsvFileService(PatientImporter patientImporter, PractitionerImporter practitionerImporter, VisitImporter visitImporter, Patient2PractitionerImporter patient2PractitionerImporter) {
        this.patientImporter = patientImporter;
        this.practitionerImporter = practitionerImporter;
        this.visitImporter = visitImporter;
        this.patient2PractitionerImporter = patient2PractitionerImporter;
    }

    @PostConstruct
    public void init() throws IOException {

        ClassPathResource resource = new ClassPathResource("csv/patients.csv");
        InputStream inputStreamCSV = resource.getInputStream();
        patientImporter.importPatients(inputStreamCSV);

        resource = new ClassPathResource("csv/practitioners.csv");
        inputStreamCSV = resource.getInputStream();
        practitionerImporter.importPractitioners(inputStreamCSV);

        resource = new ClassPathResource("csv/visits.csv");
        inputStreamCSV = resource.getInputStream();
        visitImporter.importVisits(inputStreamCSV);

        resource = new ClassPathResource("csv/patient2practitioner.csv");
        inputStreamCSV = resource.getInputStream();
        patient2PractitionerImporter.importPatient2Practitioners(inputStreamCSV);

    }

}

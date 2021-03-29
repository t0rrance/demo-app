package com.example.demo.service.importer;

import com.example.demo.repository.Patient2PractitionerRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PractitionerRepository;
import com.example.demo.repository.model.Patient2Practitioner;
import com.example.demo.service.importer.model.Patient2PractitionerRow;
import com.example.demo.service.importer.model.VisitRow;
import com.example.demo.utils.CsvParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Patient2PractitionerImporter {

    private static final Logger logger = LoggerFactory.getLogger(Patient2PractitionerImporter.class);
    private final Patient2PractitionerRepository patient2PractitionerRepository;
    private final PractitionerRepository practitionerRepository;
    private final PatientRepository patientRepository;

    public Patient2PractitionerImporter(Patient2PractitionerRepository visitRepository, PractitionerRepository practitionerRepository, PatientRepository patientRepository) {
        this.patient2PractitionerRepository = visitRepository;
        this.practitionerRepository = practitionerRepository;
        this.patientRepository = patientRepository;
    }


    @Transactional
    public void importPatient2Practitioners(InputStream file) {

        List<Patient2PractitionerRow> patient2PractitionerRowList = parseFile(file);
        List<Patient2Practitioner> patient2PractitionerList = createPatient2Practitioners(patient2PractitionerRowList);

        for (Patient2Practitioner patient2Practitioner : patient2PractitionerList) {
            if(patient2Practitioner.getPractitioner() == null || patient2Practitioner.getPatient() == null)
                logger.error(patient2Practitioner.toString());
            else
            patient2PractitionerRepository.save(patient2Practitioner);
        }

    }


    private List<Patient2Practitioner> createPatient2Practitioners(List<Patient2PractitionerRow> patient2PractitionerRows) {
        return patient2PractitionerRows.stream()
                .map(this::createPatient2Practitioner)
                .collect(Collectors.toList());
    }

    private Patient2Practitioner createPatient2Practitioner(Patient2PractitionerRow patient2PractitionerRow) {
        return Patient2Practitioner.builder()
                .practitioner(practitionerRepository.findById(patient2PractitionerRow.getPractitionerId()).orElse(null))
                .patient(patientRepository.findById(patient2PractitionerRow.getPatientId()).orElse(null))
                .build();
    }

    private List<Patient2PractitionerRow> parseFile(InputStream file) {
        return new CsvParser<>(Patient2PractitionerRow.class, file).parse();
    }

}

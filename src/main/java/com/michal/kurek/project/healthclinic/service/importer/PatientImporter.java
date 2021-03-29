package com.michal.kurek.project.healthclinic.service.importer;

import com.michal.kurek.project.healthclinic.repository.PatientRepository;
import com.michal.kurek.project.healthclinic.repository.model.Patient;
import com.michal.kurek.project.healthclinic.service.importer.model.PatientRow;
import com.michal.kurek.project.healthclinic.utils.CsvParser;
import com.michal.kurek.project.healthclinic.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientImporter {

    private final PatientRepository patientRepository;

    public PatientImporter(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public void importPatients(InputStream file) {
        List<PatientRow> patientRowList = parseFile(file);
        List<Patient> patientList = createPatients(patientRowList);
         for (Patient patient : patientList) {
            patientRepository.save(patient);
        }
    }

    private List<Patient> createPatients(List<PatientRow> patientRowList) {
        return patientRowList.stream()
                .map(this::createPatient)
                .collect(Collectors.toList());
    }

    private Patient createPatient(PatientRow patientRow) {
        return Patient.builder()
                .id(patientRow.getPatientId())
                .firstName(patientRow.getFirstName())
                .lastName(patientRow.getLastName())
                .city(patientRow.getCity())
                .createdAt(DateUtils.convert(patientRow.getCreatedAt()))
                .build();
    }

    private List<PatientRow> parseFile(InputStream file) {
        return new CsvParser<>(PatientRow.class, file).parse();
    }

}

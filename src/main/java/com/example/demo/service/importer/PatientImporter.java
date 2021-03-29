package com.example.demo.service.importer;

import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.model.Patient;
import com.example.demo.service.importer.model.PatientRow;
import com.example.demo.utils.CsvParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientImporter {

    private final PatientRepository patientRepository;

    public PatientImporter(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public void importPatients(InputStream file) {

        List<PatientRow> patientRowList = parseFile(file);
        List<Patient> patientList = createPatients(patientRowList);
         for (Patient p : patientList) {
            patientRepository.save(p);
        }

    }

    private List<Patient> createPatients(List<PatientRow> patientRowList) {
        return patientRowList.stream()
                .map(this::createObjectPatient)
                .collect(Collectors.toList());
    }

    private Patient createObjectPatient(PatientRow p) {
        return Patient.builder()
                .id(p.getPatientId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .city(p.getCity())
                .createdAt(convertToDateFormat(p.getCreatedAt()))
                .build();
    }

    private Date convertToDateFormat(String date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    private List<PatientRow> parseFile(InputStream file) {
        return new CsvParser<>(PatientRow.class, file).parse();
    }

}

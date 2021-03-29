package com.example.demo.service.importer;

import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PractitionerRepository;
import com.example.demo.repository.VisitRepository;
import com.example.demo.repository.model.Visit;
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
public class VisitImporter {

    private static final Logger logger = LoggerFactory.getLogger(VisitImporter.class);
    private final VisitRepository visitRepository;
    private final PractitionerRepository practitionerRepository;
    private final PatientRepository patientRepository;

    public VisitImporter(VisitRepository visitRepository, PractitionerRepository practitionerRepository, PatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.practitionerRepository = practitionerRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public void importVisits(InputStream file) {
        List<VisitRow> visitRowList = parseFile(file);
        List<Visit> visitList = createVisits(visitRowList);

        for (Visit visit : visitList) {
            if(visit.getPractitioner() == null || visit.getPatient() == null)
                logger.error(visit.toString());
            else
            visitRepository.save(visit);
        }
    }


    private List<Visit> createVisits(List<VisitRow> visitRows) {
        return visitRows.stream()
                .map(this::createVisit)
                .collect(Collectors.toList());
    }

    private Visit createVisit(VisitRow visitRow) {
        return Visit.builder()
                .visitId(visitRow.getVisitId())
                .practitioner(practitionerRepository.findById(visitRow.getPractitionerId()).orElse(null))
                .patient(patientRepository.findById(visitRow.getPatientId()).orElse(null))
                .build();
    }

    private List<VisitRow> parseFile(InputStream file) {
        return new CsvParser<>(VisitRow.class, file).parse();
    }

}

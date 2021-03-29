package com.michal.kurek.project.healthclinic.service.importer;

import com.michal.kurek.project.healthclinic.repository.PatientRepository;
import com.michal.kurek.project.healthclinic.repository.PractitionerRepository;
import com.michal.kurek.project.healthclinic.repository.VisitRepository;
import com.michal.kurek.project.healthclinic.repository.model.Visit;
import com.michal.kurek.project.healthclinic.service.importer.model.VisitRow;
import com.michal.kurek.project.healthclinic.utils.CsvParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VisitImporter {

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
            if (visit.getPractitioner() == null || visit.getPatient() == null) {
                log.error("Bad configuration with Id {}", visit.getVisitId());
            } else {
                visitRepository.save(visit);
            }
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

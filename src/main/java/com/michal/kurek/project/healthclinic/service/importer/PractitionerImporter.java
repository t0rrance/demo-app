package com.michal.kurek.project.healthclinic.service.importer;

import com.michal.kurek.project.healthclinic.repository.PractitionerRepository;
import com.michal.kurek.project.healthclinic.repository.model.Practitioner;
import com.michal.kurek.project.healthclinic.service.importer.model.PractitionerRow;
import com.michal.kurek.project.healthclinic.utils.CsvParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PractitionerImporter {

    private final PractitionerRepository practitionerRepository;

    public PractitionerImporter(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @Transactional
    public void importPractitioners(InputStream fileCSV) {
        List<PractitionerRow> practitionerRowList = parseFile(fileCSV);
        List<Practitioner> practitionersList = createPractitioners(practitionerRowList);
        for (Practitioner practitioner : practitionersList) {
            practitionerRepository.save(practitioner);
        }
    }

    private List<Practitioner> createPractitioners(List<PractitionerRow> practitionerRowList) {
        return practitionerRowList.stream()
                .map(this::createPractitioner)
                .collect(Collectors.toList());
    }

    private Practitioner createPractitioner(PractitionerRow practitionerRow) {
        return Practitioner.builder()
                .practitionerId(practitionerRow.getPractitionerId())
                .specialization(practitionerRow.getSpecialization())
                .build();
    }

    private List<PractitionerRow> parseFile(InputStream fileCSV) {
        return new CsvParser<>(PractitionerRow.class, fileCSV).parse();
    }

}

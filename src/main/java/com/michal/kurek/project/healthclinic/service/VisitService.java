package com.michal.kurek.project.healthclinic.service;

import com.michal.kurek.project.healthclinic.controller.model.PatientResponse;
import com.michal.kurek.project.healthclinic.controller.model.SpecializationResponse;
import com.michal.kurek.project.healthclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<PatientResponse> selectPatientVisits(String city, String specialization) {
        if (city == null || city.isEmpty() || city.equals("ALL")) {
            return visitRepository.findPatientVisitsBySpecialization(specialization);
        } else if (specialization == null || specialization.isEmpty() || specialization.equals("ALL")) {
            return visitRepository.findPatientVisitsByCity(city);
        } else {
            return visitRepository.findPatientVisitsByCityAndSpecialization(city, specialization);
        }
    }

    public List<SpecializationResponse> selectSpecializations(String specialization) {
        return visitRepository.findNumberVisitsBySpecialization(specialization);
    }

}

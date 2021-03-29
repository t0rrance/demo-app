package com.example.demo.service;

import com.example.demo.controller.model.PatientResponse;
import com.example.demo.controller.model.SpecializationReponse;
import com.example.demo.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }


    public List<PatientResponse> selectPatientVisits(String city, String specialization) {
        if(city.isEmpty() || city.equals("ALL"))  {
            List<PatientResponse> patientResponses = visitRepository.findPatientVisitsBySpecialization(specialization);
            return patientResponses;
        } else if(specialization.isEmpty() || specialization.equals("ALL")) {
            return visitRepository.findPatientVisitsByCity(city);
        } else {
            return visitRepository.findPatientVisitsByCityAndSpecialization(city,specialization);
        }
    }


    public List<SpecializationReponse> selectSpecializations(String specialization) {
        return visitRepository.findNumberVisitsBySpecialization(specialization);
    }

}

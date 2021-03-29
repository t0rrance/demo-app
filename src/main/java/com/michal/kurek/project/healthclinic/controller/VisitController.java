package com.michal.kurek.project.healthclinic.controller;

import com.michal.kurek.project.healthclinic.controller.model.PatientResponse;
import com.michal.kurek.project.healthclinic.controller.model.SpecializationResponse;
import com.michal.kurek.project.healthclinic.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService patientService) {
        this.visitService = patientService;
    }

    @ResponseBody
    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponse>> getPatientVisits(@RequestParam(name = "city", required = false) String city, @RequestParam(name = "specialization", required = false) String specialization) {
        List<PatientResponse> response = visitService.selectPatientVisits(city, specialization);
        return ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @GetMapping("/specializations")
    public ResponseEntity<List<SpecializationResponse>> getSpecializationVisits(@RequestParam(name = "specialization", required = false) String specialization) {
        List<SpecializationResponse> response = visitService.selectSpecializations(specialization);
        return ResponseEntity.ok().body(response);
    }

}

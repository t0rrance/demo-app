package com.example.demo.repository;

import com.example.demo.controller.model.PatientResponse;
import com.example.demo.controller.model.SpecializationReponse;
import com.example.demo.repository.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("select new com.example.demo.controller.model.PatientResponse(v.patient.firstName, v.patient.lastName, COUNT(*) as countVisits) from  Visit v  " +
            " where v.patient.city = :city and v.practitioner.specialization =:specialization group by v.patient.firstName, v.patient.lastName")
    List<PatientResponse> findPatientVisitsByCityAndSpecialization(@Param("city") String city, @Param("specialization") String specialization);

    @Query("select new com.example.demo.controller.model.PatientResponse(v.patient.firstName, v.patient.lastName, COUNT(*) as countVisits) from  Visit v  " +
            " where  v.patient.city = :city group by v.patient.firstName, v.patient.lastName")
    List<PatientResponse> findPatientVisitsByCity(String city);

    @Query("select new com.example.demo.controller.model.PatientResponse(v.patient.firstName, v.patient.lastName, COUNT(*) as countVisits) from  Visit v  " +
            " where  v.practitioner.specialization =:specialization group by v.patient.firstName, v.patient.lastName")
    List<PatientResponse> findPatientVisitsBySpecialization(@Param("specialization") String specialization);

    @Query("select new com.example.demo.controller.model.SpecializationReponse(v.practitioner.specialization, COUNT(*) as numberOfVisits ) from  Visit v  " +
            " where  v.practitioner.specialization =:specialization group by v.practitioner.specialization")
    List<SpecializationReponse> findNumberVisitsBySpecialization(@Param("specialization") String specialization);

}

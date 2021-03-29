package com.michal.kurek.project.healthclinic.repository;

import com.michal.kurek.project.healthclinic.repository.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
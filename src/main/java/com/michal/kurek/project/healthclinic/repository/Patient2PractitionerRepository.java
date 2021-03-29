package com.michal.kurek.project.healthclinic.repository;

import com.michal.kurek.project.healthclinic.repository.model.Patient2Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Patient2PractitionerRepository extends JpaRepository<Patient2Practitioner, Long> {
}

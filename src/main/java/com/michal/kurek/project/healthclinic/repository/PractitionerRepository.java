package com.michal.kurek.project.healthclinic.repository;

import com.michal.kurek.project.healthclinic.repository.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
}

package com.musala.alvaro.testdrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.alvaro.testdrones.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>{

}

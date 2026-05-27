package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {

    List<Visit> findByPetId(Integer petId);

    List<Visit> findByVisitDate(LocalDate visitDate);
}
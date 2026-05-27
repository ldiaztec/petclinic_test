package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {

    Visit create(Visit visit);

    Visit update(Visit visit);

    void delete(Integer id);

    Visit findById(Integer id);

    List<Visit> findAll();

    List<Visit> findByPetId(Integer petId);

    List<Visit> findByVisitDate(LocalDate visitDate);
}
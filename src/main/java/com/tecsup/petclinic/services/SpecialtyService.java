package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;

import java.util.List;

public interface SpecialtyService {

    Specialty save(Specialty specialty);

    Specialty findById(Integer id) throws SpecialtyNotFoundException;

    List<Specialty> findAll();

    void delete(Specialty specialty);
}
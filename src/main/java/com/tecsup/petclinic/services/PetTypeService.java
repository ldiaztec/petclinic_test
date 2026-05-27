package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.exceptions.PetTypeNotFoundException;

import java.util.List;

public interface PetTypeService {

    PetType save(PetType petType);

    PetType findById(Integer id) throws PetTypeNotFoundException;

    List<PetType> findAll();

    void delete(PetType petType);
}
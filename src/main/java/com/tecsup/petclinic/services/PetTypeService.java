package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import java.util.List;

public interface PetTypeService {

    PetType create(PetType petType);

    PetType update(PetType petType);

    void delete(Integer id);

    PetType findById(Integer id);

    List<PetType> findByName(String name);

    List<PetType> findByActive(Boolean active);

    List<PetType> findAll();
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import java.util.List;

public interface PetTypeService {
    PetType save(PetType petType);
    PetType findById(Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
    List<PetType> findByName(String name);
}
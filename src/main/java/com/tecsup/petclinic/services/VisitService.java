package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import java.util.List;

public interface VisitService {
    Visit save(Visit visit);
    Visit findById(Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
    List<Visit> findByPetId(Integer petId);
}
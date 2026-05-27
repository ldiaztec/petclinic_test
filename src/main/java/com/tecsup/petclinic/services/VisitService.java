package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

import java.util.List;

public interface VisitService {

    Visit save(Visit visit);

    Visit findById(Integer id) throws VisitNotFoundException;

    List<Visit> findAll();

    void delete(Visit visit);
}
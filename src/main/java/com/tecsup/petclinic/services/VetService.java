package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;

import java.util.List;

public interface VetService {

    Vet save(Vet vet);

    Vet findById(Integer id) throws VetNotFoundException;

    List<Vet> findAll();

    void delete(Vet vet);
}
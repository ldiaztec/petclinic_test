package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import java.util.List;

public interface VetService {
    Vet save(Vet vet);
    Vet findById(Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
    List<Vet> findByLastName(String lastName);
}
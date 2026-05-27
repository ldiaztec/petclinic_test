package com.tecsup.petclinic.service;

import com.tecsup.petclinic.domain.Vet;
import java.util.List;

public interface VetService {
    Vet create(Vet vet);
    Vet update(Vet vet);
    void delete(Integer id);
    Vet findById(Integer id);
    List<Vet> findByLastName(String lastName);
}
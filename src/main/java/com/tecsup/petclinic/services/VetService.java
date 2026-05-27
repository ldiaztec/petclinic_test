package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Vet;

public interface VetService {
    Vet create(Vet vet);
    Vet update(Vet vet);
    void delete(Long id);
    Vet findById(Long id);
}
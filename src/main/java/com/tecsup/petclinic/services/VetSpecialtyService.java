package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;

import java.util.List;

public interface VetSpecialtyService {

    VetSpecialty create(VetSpecialty vetSpecialty);

    VetSpecialty update(VetSpecialty vetSpecialty);

    void delete(Integer id);

    VetSpecialty findById(Integer id);

    List<VetSpecialty> findAll();
}
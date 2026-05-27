package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;

import java.util.List;

public interface VetSpecialtyService {

    VetSpecialty save(VetSpecialty vetSpecialty);

    VetSpecialty findById(Integer id) throws VetSpecialtyNotFoundException;

    List<VetSpecialty> findByVetId(Integer vetId);

    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);

    void delete(VetSpecialty vetSpecialty);
}
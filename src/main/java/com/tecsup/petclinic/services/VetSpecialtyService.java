package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;

import java.util.List;

public interface VetSpecialtyService {

    VetSpecialtyDTO assign(VetSpecialtyDTO vetSpecialtyDTO);

    List<VetSpecialtyDTO> findSpecialtiesByVetId(Integer vetId);


    List<VetSpecialtyDTO> findVetsBySpecialtyId(Integer specialtyId);

    void delete(Integer vetId, Integer specialtyId) throws VetSpecialtyNotFoundException;

    List<VetSpecialty> findAll();
}
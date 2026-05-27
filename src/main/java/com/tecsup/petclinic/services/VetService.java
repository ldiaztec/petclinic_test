package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import java.util.List;

public interface VetService {

    VetDTO create(VetDTO vetDTO);

    // Añadimos throws VetNotFoundException
    VetDTO update(VetDTO vetDTO) throws VetNotFoundException;

    // Añadimos throws VetNotFoundException
    void delete(Long id) throws VetNotFoundException;

    // Añadimos throws VetNotFoundException
    VetDTO findById(Long id) throws VetNotFoundException;

    List<VetDTO> findAll();
}
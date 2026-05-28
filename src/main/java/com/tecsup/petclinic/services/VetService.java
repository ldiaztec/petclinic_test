package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;

import java.util.List;

public interface VetService {

    VetDTO create(VetDTO vetDTO);

    VetDTO update(VetDTO vetDTO);

    VetDTO findById(Integer id);

    List<VetDTO> findAll();

    void delete(Integer id);
}
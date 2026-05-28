package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;

import java.util.List;

public interface VetSpecialtyService {

    VetSpecialtyDTO create(VetSpecialtyDTO vetSpecialtyDTO);

    List<VetSpecialtyDTO> findAll();

    List<VetSpecialtyDTO> findByVetId(Integer vetId);

    List<VetSpecialtyDTO> findBySpecialtyId(Integer specialtyId);

    void delete(Integer vetId, Integer specialtyId);

}
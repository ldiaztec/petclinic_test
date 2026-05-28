package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;

import java.util.List;

public interface OwnerService {

    OwnerDTO create(OwnerDTO ownerDTO);

    OwnerDTO update(OwnerDTO ownerDTO);

    void delete(Integer id);

    OwnerDTO findById(Integer id);

    List<OwnerDTO> findAll();

}
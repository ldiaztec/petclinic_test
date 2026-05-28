package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialityDTO;

import java.util.List;

public interface SpecialityService {

    SpecialityDTO create(SpecialityDTO specialityDTO);

    SpecialityDTO update(SpecialityDTO specialityDTO);

    void delete(Integer id);

    SpecialityDTO findById(Integer id);

    List<SpecialityDTO> findAll();

}
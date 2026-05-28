package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;

import java.util.List;

public interface TypeService {

    TypeDTO create(TypeDTO typeDTO);

    TypeDTO update(TypeDTO typeDTO);

    void delete(Integer id);

    TypeDTO findById(Integer id);

    List<TypeDTO> findAll();

}
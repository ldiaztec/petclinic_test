package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;

import java.util.List;

public interface VisitService {

    VisitDTO create(VisitDTO visitDTO);

    VisitDTO update(VisitDTO visitDTO);

    void delete(Integer id);

    VisitDTO findById(Integer id);

    List<VisitDTO> findAll();

}
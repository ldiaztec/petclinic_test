package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty findById(Integer id) throws SpecialtyNotFoundException {
        return specialtyRepository.findById(id)
            .orElseThrow(() -> new SpecialtyNotFoundException(
                "Especialidad no encontrada con id: " + id));
    }

    @Override
    public List<Specialty> findAll() {
        return (List<Specialty>) specialtyRepository.findAll();
    }

    @Override
    public void delete(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }
}
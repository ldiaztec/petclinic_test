package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty findById(Integer id) throws Exception {
        Optional<Specialty> specialty = specialtyRepository.findById(id);
        if (specialty.isPresent()) {
            return specialty.get();
        } else {
            throw new Exception("Especialidad no encontrada con el ID: " + id);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Specialty specialty = findById(id);
        specialtyRepository.delete(specialty);
    }

    @Override
    public List<Specialty> findByName(String name) {
        return specialtyRepository.findByName(name);
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Integer id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public Specialty findById(Integer id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Specialty> findByName(String name) {
        List<Specialty> specialties = specialtyRepository.findByName(name);
        specialties.forEach(s -> log.info("{}", s));
        return specialties;
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }
}
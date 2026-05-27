package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.repositories.PetTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType create(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public PetType update(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public void delete(Integer id) {
        petTypeRepository.deleteById(id);
    }

    @Override
    public PetType findById(Integer id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<PetType> findByName(String name) {
        List<PetType> types = petTypeRepository.findByName(name);
        types.forEach(t -> log.info("{}", t));
        return types;
    }

    @Override
    public List<PetType> findByActive(Boolean active) {
        List<PetType> types = petTypeRepository.findByActive(active);
        types.forEach(t -> log.info("{}", t));
        return types;
    }

    @Override
    public List<PetType> findAll() {
        return petTypeRepository.findAll();
    }
}
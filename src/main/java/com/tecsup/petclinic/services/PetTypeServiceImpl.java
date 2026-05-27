package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.exceptions.PetTypeNotFoundException;
import com.tecsup.petclinic.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetTypeServiceImpl implements PetTypeService {

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public PetType findById(Integer id) throws PetTypeNotFoundException {
        return petTypeRepository.findById(id)
            .orElseThrow(() -> new PetTypeNotFoundException(
                "Tipo de mascota no encontrado con id: " + id));
    }

    @Override
    public List<PetType> findAll() {
        return (List<PetType>) petTypeRepository.findAll();
    }

    @Override
    public void delete(PetType petType) {
        petTypeRepository.delete(petType);
    }
}
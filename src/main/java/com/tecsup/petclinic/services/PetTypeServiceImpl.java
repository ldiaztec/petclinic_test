package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PetTypeServiceImpl implements PetTypeService {

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public PetType findById(Integer id) throws Exception {
        Optional<PetType> petType = petTypeRepository.findById(id);
        if (petType.isPresent()) {
            return petType.get();
        } else {
            throw new Exception("Tipo de mascota no encontrado con el ID: " + id);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        PetType petType = findById(id);
        petTypeRepository.delete(petType);
    }

    @Override
    public List<PetType> findByName(String name) {
        return petTypeRepository.findByName(name);
    }
}
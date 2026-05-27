package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exceptions.PetNotFoundException;
import com.tecsup.petclinic.mappers.PetMapper;
import com.tecsup.petclinic.repositories.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    @Override
    public PetDTO create(PetDTO petDTO) {
        Pet pet = petMapper.mapToEntity(petDTO);
        Pet savedPet = petRepository.save(pet);
        return petMapper.mapToDto(savedPet);
    }

    @Override
    public PetDTO update(PetDTO petDTO) {
        Pet pet = petMapper.mapToEntity(petDTO);
        Pet updatedPet = petRepository.save(pet);
        return petMapper.mapToDto(updatedPet);
    }

    @Override
    public void delete(Integer id) throws PetNotFoundException {
        findById(id);
        petRepository.deleteById(id);
    }

    @Override
    public PetDTO findById(Integer id) throws PetNotFoundException {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet id: " + id + " not found"));
        return petMapper.mapToDto(pet);
    }

    @Override
    public List<PetDTO> findByName(String name) {
        List<Pet> pets = petRepository.findByName(name);
        return pets.stream()
                .map(petMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pet> findByTypeId(int typeId) {
        return petRepository.findByTypeId(typeId);
    }

    @Override
    public List<Pet> findByOwnerId(int ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Pet> findAll() {
        return (List<Pet>) petRepository.findAll();
    }
}
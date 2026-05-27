package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.mappers.OwnerMapper;
import com.tecsup.petclinic.repositories.OwnerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("null") 
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public OwnerDTO create(OwnerDTO ownerDTO) {
        Owner newOwner = ownerRepository.save(ownerMapper.mapToEntity(ownerDTO));
        return ownerMapper.mapToDto(newOwner);
    }

    @Override
    public OwnerDTO update(OwnerDTO ownerDTO) {
        Owner updatedOwner = ownerRepository.save(ownerMapper.mapToEntity(ownerDTO));
        return ownerMapper.mapToDto(updatedOwner);
    }

    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        OwnerDTO ownerDTO = findById(id);
        ownerRepository.delete(ownerMapper.mapToEntity(ownerDTO));
    }

    @Override
    public OwnerDTO findById(Integer id) throws OwnerNotFoundException {
        if (id == null) {
            throw new OwnerNotFoundException("El ID provisto es nulo");
        }
        
        Optional<Owner> owner = ownerRepository.findById(id);
        if (!owner.isPresent()) {
            throw new OwnerNotFoundException("¡Registro de dueño no encontrado!");
        }
        return ownerMapper.mapToDto(owner.get());
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
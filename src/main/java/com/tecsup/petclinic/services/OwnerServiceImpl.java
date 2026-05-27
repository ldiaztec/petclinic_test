package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new OwnerNotFoundException(
                "Dueño no encontrado con id: " + id));
    }

    @Override
    public List<Owner> findAll() {
        return (List<Owner>) ownerRepository.findAll();
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }
}
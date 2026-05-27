package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    // 1. Crear
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    // 2. Buscar por ID
    public Owner findById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.orElse(null); // Retorna null si no lo encuentra
    }

    // 3. Actualizar
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    // 4. Eliminar
    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }
}
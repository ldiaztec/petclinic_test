package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Integer id) throws Exception {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            return owner.get();
        } else {
            throw new Exception("Dueño no encontrado con el ID: " + id);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }
    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }
    @Override
    public void delete(Integer id) {
        ownerRepository.deleteById(id);
    }
    @Override
    public Owner findById(Integer id) {
        return ownerRepository.findById(id).orElse(null);
    }
    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
    @Override
    public List<Owner> findByCity(String city) {
        return ownerRepository.findByCity(city);
    }
}
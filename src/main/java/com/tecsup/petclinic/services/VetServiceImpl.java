package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetServiceImpl implements VetService {
    @Autowired
    private VetRepository vetRepository;
    @Override
    public Vet create(Vet vet) {
        return vetRepository.save(vet);
    }
    @Override
    public Vet update(Vet vet) {
        return vetRepository.save(vet);
    }
    @Override
    public void delete(Integer id) {
        vetRepository.deleteById(id);
    }
    @Override
    public Vet findById(Integer id) {
        return vetRepository.findById(id).orElse(null);
    }
    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }
    @Override
    public List<Vet> findByLastName(String lastName) {
        return vetRepository.findByLastName(lastName);
    }
    @Override
    public List<Vet> findByActive(Boolean active) {
        return vetRepository.findByActive(active);
    }
}
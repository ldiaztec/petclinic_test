package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetServiceImpl implements VetService {

    @Autowired
    private VetRepository vetRepository;

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Vet findById(Integer id) throws VetNotFoundException {
        return vetRepository.findById(id)
            .orElseThrow(() -> new VetNotFoundException(
                "Veterinario no encontrado con id: " + id));
    }

    @Override
    public List<Vet> findAll() {
        return (List<Vet>) vetRepository.findAll();
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }
}
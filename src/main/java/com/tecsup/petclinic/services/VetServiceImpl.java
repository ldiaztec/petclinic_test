package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet create(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Vet update(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Long id) throws VetNotFoundException {
        Vet vet = findById(id);
        vetRepository.delete(vet);
    }

    @Override
    public Vet findById(Long id) throws VetNotFoundException {
        Optional<Vet> vet = vetRepository.findById(id);
        return vet.orElseThrow(() -> new VetNotFoundException("Vet not found with id " + id));
    }

    @Override
    public List<Vet> findByName(String name) {
        List<Vet> vets = vetRepository.findByName(name);
        vets.forEach(vet -> log.info("" + vet));
        return vets;
    }

    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }
}
package com.tecsup.petclinic.service;

import com.tecsup.petclinic.domain.Vet;
import com.tecsup.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    // Inyección por constructor (Sin @Autowired, siguiendo las reglas del proyecto)
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
    public void delete(Integer id) {
        vetRepository.deleteById(id);
    }

    @Override
    public Vet findById(Integer id) {
        Optional<Vet> vet = vetRepository.findById(id);
        return vet.orElseThrow(() -> new RuntimeException("Vet not found"));
    }

    @Override
    public List<Vet> findByLastName(String lastName) {
        return vetRepository.findByLastName(lastName);
    }
}
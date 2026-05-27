package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SpecialtyService {
    @Autowired private SpecialtyRepository repository;
    public Specialty create(Specialty s) { return repository.save(s); }
    public Specialty findById(Long id) { return repository.findById(id).orElse(null); }
    public Specialty update(Specialty s) { return repository.save(s); }
    public void delete(Long id) { repository.deleteById(id); }
}
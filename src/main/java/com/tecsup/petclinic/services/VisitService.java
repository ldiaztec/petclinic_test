package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VisitService {
    @Autowired private VisitRepository repository;
    public Visit create(Visit v) { return repository.save(v); }
    public Visit findById(Long id) { return repository.findById(id).orElse(null); }
    public Visit update(Visit v) { return repository.save(v); }
    public void delete(Long id) { repository.deleteById(id); }
}
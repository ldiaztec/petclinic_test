package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    @Autowired private TypeRepository repository;
    public Type create(Type t) { return repository.save(t); }
    public Type findById(Long id) { return repository.findById(id).orElse(null); }
    public Type update(Type t) { return repository.save(t); }
    public void delete(Long id) { repository.deleteById(id); }
}
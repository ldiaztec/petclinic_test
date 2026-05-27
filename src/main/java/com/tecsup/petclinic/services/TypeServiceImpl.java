package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Type create(Type type) {
        return typeRepository.save(type);
    }
    @Override
    public Type update(Type type) {
        return typeRepository.save(type);
    }
    @Override
    public void delete(Integer id) {
        typeRepository.deleteById(id);
    }
    @Override
    public Type findById(Integer id) {
        return typeRepository.findById(id).orElse(null);
    }
    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }
    @Override
    public List<Type> findByName(String name) {
        return typeRepository.findByName(name);
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type save(Type type) {
        return typeRepository.save(type);
    }

    public Type findById(Integer id) {
        Optional<Type> type = typeRepository.findById(id);
        if (type.isPresent()) {
            return type.get();
        } else {
            throw new RuntimeException("Tipo de mascota no encontrado con el ID: " + id);
        }
    }

    public void deleteById(Integer id) {
        typeRepository.deleteById(id);
    }
}
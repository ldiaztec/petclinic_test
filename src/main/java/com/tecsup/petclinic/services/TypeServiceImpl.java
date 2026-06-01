package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type findById(Integer id) throws Exception {

        Optional<Type> type = typeRepository.findById(id);

        if (type.isPresent()) {
            return type.get();
        }

        throw new Exception("Tipo no encontrado");
    }

    @Override
    public void deleteById(Integer id) throws Exception {

        Type type = findById(id);

        typeRepository.delete(type);
    }
}
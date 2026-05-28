package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;
import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public TypeDTO create(TypeDTO typeDTO) {

        Type type = new Type(
                null,
                typeDTO.getName()
        );

        type = typeRepository.save(type);

        typeDTO.setId(type.getId());

        return typeDTO;
    }

    @Override
    public TypeDTO update(TypeDTO typeDTO) {

        Type type = new Type(
                typeDTO.getId(),
                typeDTO.getName()
        );

        typeRepository.save(type);

        return typeDTO;
    }

    @Override
    public void delete(Integer id) {

        typeRepository.deleteById(id);
    }

    @Override
    public TypeDTO findById(Integer id) {

        Type type = typeRepository.findById(id).orElse(null);

        if (type == null) {
            return null;
        }

        return new TypeDTO(
                type.getId(),
                type.getName()
        );
    }

    @Override
    public List<TypeDTO> findAll() {

        return typeRepository.findAll()
                .stream()
                .map(type -> new TypeDTO(
                        type.getId(),
                        type.getName()
                ))
                .collect(Collectors.toList());
    }
}
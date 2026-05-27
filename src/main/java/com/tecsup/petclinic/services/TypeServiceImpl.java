package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;
import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;
import com.tecsup.petclinic.mappers.TypeMapper;
import com.tecsup.petclinic.repositories.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    @Override
    public TypeDTO create(TypeDTO typeDTO) {
        Type type = typeMapper.mapToEntity(typeDTO);
        Type newType = typeRepository.save(type);
        return typeMapper.mapToDto(newType);
    }

    @Override
    public TypeDTO update(TypeDTO typeDTO) throws TypeNotFoundException {
        if (!typeRepository.existsById(typeDTO.getId())) {
            throw new TypeNotFoundException("Type record not found...!");
        }
        Type type = typeMapper.mapToEntity(typeDTO);
        Type updatedType = typeRepository.save(type);
        return typeMapper.mapToDto(updatedType);
    }

    @Override
    public void delete(Integer id) throws TypeNotFoundException {
        TypeDTO typeDTO = findById(id);
        Type type = typeMapper.mapToEntity(typeDTO);
        typeRepository.delete(type);
    }

    @Override
    public TypeDTO findById(Integer id) throws TypeNotFoundException {
        Optional<Type> type = typeRepository.findById(id);
        if (!type.isPresent()) {
            throw new TypeNotFoundException("Type record not found...!");
        }
        return typeMapper.mapToDto(type.get());
    }

    @Override
    public List<TypeDTO> findAll() {
        return typeRepository.findAll().stream()
                .map(typeMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
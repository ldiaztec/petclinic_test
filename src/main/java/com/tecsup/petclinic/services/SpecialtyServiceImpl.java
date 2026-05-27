package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.mappers.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    @Override
    public SpecialtyDTO create(SpecialtyDTO specialtyDTO) {
        Specialty specialty = specialtyMapper.mapToEntity(specialtyDTO);
        Specialty newSpecialty = specialtyRepository.save(specialty);
        return specialtyMapper.mapToDto(newSpecialty);
    }

    @Override
    public SpecialtyDTO update(SpecialtyDTO specialtyDTO) throws SpecialtyNotFoundException {
        if (!specialtyRepository.existsById(specialtyDTO.getId())) {
            throw new SpecialtyNotFoundException("Specialty record not found...!");
        }
        Specialty specialty = specialtyMapper.mapToEntity(specialtyDTO);
        Specialty updatedSpecialty = specialtyRepository.save(specialty);
        return specialtyMapper.mapToDto(updatedSpecialty);
    }

    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {
        SpecialtyDTO specialtyDTO = findById(id);
        Specialty specialty = specialtyMapper.mapToEntity(specialtyDTO);
        specialtyRepository.delete(specialty);
    }

    @Override
    public SpecialtyDTO findById(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findById(id);
        if (!specialty.isPresent()) {
            throw new SpecialtyNotFoundException("Specialty record not found...!");
        }
        return specialtyMapper.mapToDto(specialty.get());
    }

    @Override
    public List<SpecialtyDTO> findAll() {
        return specialtyRepository.findAll().stream()
                .map(specialtyMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
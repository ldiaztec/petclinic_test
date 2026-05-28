package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialityDTO;
import com.tecsup.petclinic.entities.Speciality;
import com.tecsup.petclinic.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public SpecialityDTO create(SpecialityDTO specialityDTO) {

        Speciality speciality = new Speciality(
                null,
                specialityDTO.getName()
        );

        speciality = specialityRepository.save(speciality);

        specialityDTO.setId(speciality.getId());

        return specialityDTO;
    }

    @Override
    public SpecialityDTO update(SpecialityDTO specialityDTO) {

        Speciality speciality = new Speciality(
                specialityDTO.getId(),
                specialityDTO.getName()
        );

        specialityRepository.save(speciality);

        return specialityDTO;
    }

    @Override
    public void delete(Integer id) {

        specialityRepository.deleteById(id);
    }

    @Override
    public SpecialityDTO findById(Integer id) {

        Speciality speciality = specialityRepository.findById(id).orElse(null);

        if (speciality == null) {
            return null;
        }

        return new SpecialityDTO(
                speciality.getId(),
                speciality.getName()
        );
    }

    @Override
    public List<SpecialityDTO> findAll() {

        return specialityRepository.findAll()
                .stream()
                .map(speciality -> new SpecialityDTO(
                        speciality.getId(),
                        speciality.getName()
                ))
                .collect(Collectors.toList());
    }
}
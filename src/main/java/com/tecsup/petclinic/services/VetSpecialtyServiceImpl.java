package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    @Autowired
    private VetSpecialtyRepository vetSpecialtyRepository;

    @Override
    public VetSpecialtyDTO create(VetSpecialtyDTO dto) {

        VetSpecialty vetSpecialty = new VetSpecialty(
                dto.getVetId(),
                dto.getSpecialtyId()
        );

        vetSpecialtyRepository.save(vetSpecialty);

        return dto;
    }

    @Override
    public List<VetSpecialtyDTO> findAll() {

        return vetSpecialtyRepository.findAll()
                .stream()
                .map(v -> new VetSpecialtyDTO(
                        v.getVetId(),
                        v.getSpecialtyId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<VetSpecialtyDTO> findByVetId(Integer vetId) {

        return vetSpecialtyRepository.findByVetId(vetId)
                .stream()
                .map(v -> new VetSpecialtyDTO(
                        v.getVetId(),
                        v.getSpecialtyId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<VetSpecialtyDTO> findBySpecialtyId(Integer specialtyId) {

        return vetSpecialtyRepository.findBySpecialtyId(specialtyId)
                .stream()
                .map(v -> new VetSpecialtyDTO(
                        v.getVetId(),
                        v.getSpecialtyId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer vetId, Integer specialtyId) {

        VetSpecialtyId id = new VetSpecialtyId(vetId, specialtyId);

        vetSpecialtyRepository.deleteById(id);
    }
}
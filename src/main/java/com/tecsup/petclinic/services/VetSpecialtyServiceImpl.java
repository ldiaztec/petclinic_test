package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    private final VetSpecialtyRepository vetSpecialtyRepository;

    @Override
    @Transactional
    public VetSpecialtyDTO assignSpecialty(Integer vetId, Integer specialtyId) {
        // Evitar duplicados si ya existe la relación
        var existing = vetSpecialtyRepository.findByVetIdAndSpecialtyId(vetId, specialtyId);
        if (existing.isPresent()) {
            return VetSpecialtyDTO.builder().vetId(vetId).specialtyId(specialtyId).build();
        }

        VetSpecialty relation = VetSpecialty.builder()
                .vetId(vetId)
                .specialtyId(specialtyId)
                .build();

        vetSpecialtyRepository.save(relation);
        return VetSpecialtyDTO.builder().vetId(vetId).specialtyId(specialtyId).build();
    }

    @Override
    public List<Integer> findSpecialtiesByVet(Integer vetId) {
        return vetSpecialtyRepository.findByVetId(vetId).stream()
                .map(VetSpecialty::getSpecialtyId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> findVetsBySpecialty(Integer specialtyId) {
        return vetSpecialtyRepository.findBySpecialtyId(specialtyId).stream()
                .map(VetSpecialty::getVetId)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeRelation(Integer vetId, Integer specialtyId) {
        vetSpecialtyRepository.findByVetIdAndSpecialtyId(vetId, specialtyId)
                .ifPresent(vetSpecialtyRepository::delete);
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    private final VetSpecialtyRepository vetSpecialtyRepository;

    public VetSpecialtyServiceImpl(VetSpecialtyRepository vetSpecialtyRepository) {
        this.vetSpecialtyRepository = vetSpecialtyRepository;
    }

    @Override
    public VetSpecialty assign(VetSpecialty vetSpecialty) {
        return vetSpecialtyRepository.save(vetSpecialty);
    }

    @Override
    public VetSpecialty update(VetSpecialty vetSpecialty) {
        return vetSpecialtyRepository.save(vetSpecialty);
    }

    @Override
    public void delete(Integer vetId, Integer specialtyId) throws VetSpecialtyNotFoundException {
        findById(vetId, specialtyId);
        vetSpecialtyRepository.deleteById(new VetSpecialtyId(vetId, specialtyId));
    }

    @Override
    public VetSpecialty findById(Integer vetId, Integer specialtyId) throws VetSpecialtyNotFoundException {
        return vetSpecialtyRepository.findById(new VetSpecialtyId(vetId, specialtyId))
                .orElseThrow(() -> new VetSpecialtyNotFoundException(
                        "VetSpecialty not found for vet: " + vetId + " specialty: " + specialtyId));
    }

    @Override
    public List<VetSpecialty> findByVetId(Integer vetId) {
        return vetSpecialtyRepository.findByVetId(vetId);
    }

    @Override
    public List<VetSpecialty> findBySpecialtyId(Integer specialtyId) {
        return vetSpecialtyRepository.findBySpecialtyId(specialtyId);
    }
}
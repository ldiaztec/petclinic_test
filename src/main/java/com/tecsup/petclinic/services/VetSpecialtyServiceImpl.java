package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    @Autowired
    private VetSpecialtyRepository vetSpecialtyRepository;

    @Override
    public VetSpecialty save(VetSpecialty vetSpecialty) {
        return vetSpecialtyRepository.save(vetSpecialty);
    }

    @Override
    public VetSpecialty findById(Integer id) throws VetSpecialtyNotFoundException {
        return vetSpecialtyRepository.findById(id)
            .orElseThrow(() -> new VetSpecialtyNotFoundException(
                "Relación Vet-Specialty no encontrada con id: " + id));
    }

    @Override
    public List<VetSpecialty> findByVetId(Integer vetId) {
        return vetSpecialtyRepository.findByVetId(vetId);
    }

    @Override
    public List<VetSpecialty> findBySpecialtyId(Integer specialtyId) {
        return vetSpecialtyRepository.findBySpecialtyId(specialtyId);
    }

    @Override
    public void delete(VetSpecialty vetSpecialty) {
        vetSpecialtyRepository.delete(vetSpecialty);
    }
}
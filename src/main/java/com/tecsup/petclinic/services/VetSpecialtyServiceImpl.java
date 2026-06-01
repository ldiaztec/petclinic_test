package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    @Autowired
    private VetSpecialtyRepository repository;

    @Override
    public VetSpecialty save(VetSpecialty vetSpecialty) {
        return repository.save(vetSpecialty);
    }

    @Override
    public List<VetSpecialty> findByVetId(Integer vetId) {
        return repository.findByVetId(vetId);
    }

    @Override
    public List<VetSpecialty> findBySpecialtyId(Integer specialtyId) {
        return repository.findBySpecialtyId(specialtyId);
    }

    @Override
    public void deleteById(VetSpecialtyId id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Relación no encontrada");
        }
        repository.deleteById(id);
    }

    @Override
    public VetSpecialty findById(VetSpecialtyId id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Relación no encontrada con ID: " + id));
    }
}

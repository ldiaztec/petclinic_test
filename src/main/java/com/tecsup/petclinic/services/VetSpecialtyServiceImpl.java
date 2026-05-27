package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    @Autowired
    private VetSpecialtyRepository vetSpecialtyRepository;

    @Override
    public VetSpecialty assignSpecialtyToVet(Integer vetId, Integer specialtyId) {
        VetSpecialty vetSpecialty = VetSpecialty.builder()
                .vetId(vetId)
                .specialtyId(specialtyId)
                .build();

        return vetSpecialtyRepository.save(vetSpecialty);
    }
    @Override
    public List<VetSpecialty> findSpecialtiesByVet(Integer vetId) {
        return vetSpecialtyRepository.findByVetId(vetId);
    }
    @Override
    public List<VetSpecialty> findVetsBySpecialty(Integer specialtyId) {
        return vetSpecialtyRepository.findBySpecialtyId(specialtyId);
    }
    @Override
    public void deleteRelation(Integer vetId, Integer specialtyId) {
        VetSpecialtyId id = new VetSpecialtyId(vetId, specialtyId);
        vetSpecialtyRepository.deleteById(id);
    }
    @Override
    public VetSpecialty findByIds(Integer vetId, Integer specialtyId) {
        VetSpecialtyId id = new VetSpecialtyId(vetId, specialtyId);
        return vetSpecialtyRepository.findById(id).orElse(null);
    }
}
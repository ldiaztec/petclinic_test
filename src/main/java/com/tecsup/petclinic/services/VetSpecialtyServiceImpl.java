package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
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
    public void delete(Integer vetId, Integer specialtyId) throws Exception {
        // Buscamos las relaciones existentes para ese veterinario
        List<VetSpecialty> relations = vetSpecialtyRepository.findByVetId(vetId);
        
        // Filtramos la que coincide con la especialidad y la borramos
        VetSpecialty toDelete = relations.stream()
                .filter(r -> r.getSpecialtyId().equals(specialtyId))
                .findFirst()
                .orElseThrow(() -> new Exception("Relación no encontrada"));
                
        vetSpecialtyRepository.delete(toDelete);
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
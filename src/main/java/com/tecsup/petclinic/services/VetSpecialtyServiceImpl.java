package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
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
    public VetSpecialty create(VetSpecialty vetSpecialty) {
        return vetSpecialtyRepository.save(vetSpecialty);
    }

    @Override
    public VetSpecialty update(VetSpecialty vetSpecialty) {
        return vetSpecialtyRepository.save(vetSpecialty);
    }

    @Override
    public void delete(VetSpecialtyId id) {
        vetSpecialtyRepository.deleteById(id);
    }

    @Override
    public VetSpecialty findById(VetSpecialtyId id) {
        return vetSpecialtyRepository.findById(id).orElse(null);
    }

    @Override
    public List<VetSpecialty> findByVetId(Integer vetId) {
        List<VetSpecialty> list = vetSpecialtyRepository.findById_VetId(vetId);
        list.forEach(v -> log.info("{}", v));
        return list;
    }

    @Override
    public List<VetSpecialty> findBySpecialtyId(Integer specialtyId) {
        List<VetSpecialty> list = vetSpecialtyRepository.findById_SpecialtyId(specialtyId);
        list.forEach(v -> log.info("{}", v));
        return list;
    }

    @Override
    public List<VetSpecialty> findAll() {
        return vetSpecialtyRepository.findAll();
    }
}
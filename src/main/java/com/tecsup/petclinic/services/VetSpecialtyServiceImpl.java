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
    public VetSpecialty create(VetSpecialty vetSpecialty) {
        return vetSpecialtyRepository.save(vetSpecialty);
    }
    @Override
    public VetSpecialty update(VetSpecialty vetSpecialty) {
        return vetSpecialtyRepository.save(vetSpecialty);
    }
    @Override
    public void delete(Integer id) {
        vetSpecialtyRepository.deleteById(id);
    }
    @Override
    public VetSpecialty findById(Integer id) {
        return vetSpecialtyRepository.findById(id).orElse(null);
    }
    @Override
    public List<VetSpecialty> findAll() {
        return vetSpecialtyRepository.findAll();
    }
}
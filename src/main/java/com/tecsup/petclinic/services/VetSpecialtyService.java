package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import java.util.List;

public interface VetSpecialtyService {
    VetSpecialty save(VetSpecialty vetSpecialty);
    List<VetSpecialty> findByVetId(Integer vetId);
    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);
    void deleteById(VetSpecialtyId id) throws Exception;
    VetSpecialty findById(VetSpecialtyId id) throws Exception;
}

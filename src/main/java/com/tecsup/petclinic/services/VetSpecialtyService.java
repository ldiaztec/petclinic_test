package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import java.util.List;

public interface VetSpecialtyService {
    VetSpecialty save(VetSpecialty vetSpecialty);
    void delete(Integer vetId, Integer specialtyId) throws Exception; // Cambiado
    List<VetSpecialty> findByVetId(Integer vetId);
    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);
}
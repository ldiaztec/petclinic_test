package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import java.util.List;

public interface VetSpecialtyService {

    VetSpecialty create(VetSpecialty vetSpecialty);

    VetSpecialty update(VetSpecialty vetSpecialty);

    void delete(VetSpecialtyId id);

    VetSpecialty findById(VetSpecialtyId id);

    List<VetSpecialty> findByVetId(Integer vetId);

    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);

    List<VetSpecialty> findAll();
}
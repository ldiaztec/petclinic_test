package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import java.util.List;

public interface VetSpecialtyService {

    VetSpecialty assign(VetSpecialty vetSpecialty);

    VetSpecialty update(VetSpecialty vetSpecialty);

    void delete(Integer vetId, Integer specialtyId) throws VetSpecialtyNotFoundException;

    VetSpecialty findById(Integer vetId, Integer specialtyId) throws VetSpecialtyNotFoundException;

    List<VetSpecialty> findByVetId(Integer vetId);

    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);
}
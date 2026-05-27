package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface VetSpecialtyRepository extends CrudRepository<VetSpecialty, VetSpecialtyId> {

    List<VetSpecialty> findByVetId(Integer vetId);

    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);
}
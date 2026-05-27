package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.VetSpecialty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VetSpecialtyRepository extends CrudRepository<VetSpecialty, Integer> {
    // Buscar todas las especialidades asignadas a un veterinario específico
    List<VetSpecialty> findByVetId(Integer vetId);
    
    // Buscar todos los veterinarios que tienen una especialidad específica
    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);
}
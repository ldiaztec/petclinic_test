package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.VetSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetSpecialtyRepository extends JpaRepository<VetSpecialty, VetSpecialty.VetSpecialtyId> {
    
    List<VetSpecialty> findByVetId(Integer vetId);
    
    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);
    
    Optional<VetSpecialty> findByVetIdAndSpecialtyId(Integer vetId, Integer specialtyId);
}
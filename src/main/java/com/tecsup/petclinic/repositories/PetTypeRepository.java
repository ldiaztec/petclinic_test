package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Integer> {
}
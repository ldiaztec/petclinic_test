package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Integer> {
    List<PetType> findByName(String name);
}
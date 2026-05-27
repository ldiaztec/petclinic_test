package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Specialty;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SpecialtyRepository extends CrudRepository<Specialty, Integer> {

    List<Specialty> findByName(String name);
}
package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Visit;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Integer> {

    List<Visit> findByPetId(Integer petId);

    List<Visit> findByVetId(Integer vetId);
}
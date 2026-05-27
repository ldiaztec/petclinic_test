package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {
}
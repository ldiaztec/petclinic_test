package com.tecsup.petclinic.repository;

import com.tecsup.petclinic.domain.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends CrudRepository<Vet, Integer> {
    List<Vet> findByLastName(String lastName);
}
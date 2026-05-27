package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {

    List<Owner> findByLastName(String lastName);
}
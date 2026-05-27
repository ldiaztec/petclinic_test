package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Type;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TypeRepository extends CrudRepository<Type, Integer> {

    List<Type> findByName(String name);
}
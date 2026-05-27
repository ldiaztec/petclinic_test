package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    List<Type> findByName(String name);
}
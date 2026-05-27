package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

    List<Speciality> findByName(String name);
}
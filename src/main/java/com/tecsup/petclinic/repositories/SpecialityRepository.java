package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

}
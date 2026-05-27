package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpecialityRepository
        extends JpaRepository<Speciality, Integer> {

    List<Speciality> findByName(String name);

    @Override
    List<Speciality> findAll();

}
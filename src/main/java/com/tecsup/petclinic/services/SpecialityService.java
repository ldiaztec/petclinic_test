package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Speciality;

import java.util.List;

public interface SpecialityService {

    Speciality create(Speciality speciality);

    Speciality update(Speciality speciality);

    void delete(Integer id);

    Speciality findById(Integer id);

    List<Speciality> findAll();
}
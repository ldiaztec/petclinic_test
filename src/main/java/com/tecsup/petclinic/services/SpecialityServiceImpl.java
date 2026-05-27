
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Speciality;
import com.tecsup.petclinic.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Speciality create(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality update(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public void delete(Integer id) {
        specialityRepository.deleteById(id);
    }

    @Override
    public Speciality findById(Integer id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Speciality> findAll() {
        return specialityRepository.findAll();
    }
}
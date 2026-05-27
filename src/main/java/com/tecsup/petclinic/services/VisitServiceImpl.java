package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Override
    public Visit create(Visit visit) {
        return visitRepository.save(visit);
    }
    @Override
    public Visit update(Visit visit) {
        return visitRepository.save(visit);
    }
    @Override
    public void delete(Integer id) {
        visitRepository.deleteById(id);
    }
    @Override
    public Visit findById(Integer id) {
        return visitRepository.findById(id).orElse(null);
    }
    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }
    @Override
    public List<Visit> findByPetId(Integer petId) {
        return visitRepository.findByPetId(petId);
    }
    @Override
    public List<Visit> findByVisitDate(LocalDate visitDate) {
        return visitRepository.findByVisitDate(visitDate);
    }
}
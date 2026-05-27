package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit findById(Integer id) throws VisitNotFoundException {
        return visitRepository.findById(id)
            .orElseThrow(() -> new VisitNotFoundException(
                "Visita no encontrada con id: " + id));
    }

    @Override
    public List<Visit> findAll() {
        return (List<Visit>) visitRepository.findAll();
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    public Visit findById(Integer id) {
        Optional<Visit> visit = visitRepository.findById(id);
        if (visit.isPresent()) {
            return visit.get();
        } else {
            throw new RuntimeException("Visita no encontrada con el ID: " + id);
        }
    }

    public void deleteById(Integer id) {
        visitRepository.deleteById(id);
    }
}
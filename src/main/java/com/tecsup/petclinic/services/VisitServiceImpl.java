package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit findById(Integer id) throws Exception {
        Optional<Visit> visit = visitRepository.findById(id);
        if (visit.isPresent()) {
            return visit.get();
        } else {
            throw new Exception("Visita no encontrada con el ID: " + id);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Visit visit = findById(id);
        visitRepository.delete(visit);
    }

    @Override
    public List<Visit> findByPetId(Integer petId) {
        return visitRepository.findByPetId(petId);
    }
}
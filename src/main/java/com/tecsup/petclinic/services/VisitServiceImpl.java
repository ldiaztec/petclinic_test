package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

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
    public List<Visit> findByPetId(Integer petId) {
        List<Visit> visits = visitRepository.findByPetId(petId);
        visits.forEach(v -> log.info("{}", v));
        return visits;
    }

    @Override
    public List<Visit> findByVetId(Integer vetId) {
        List<Visit> visits = visitRepository.findByVetId(vetId);
        visits.forEach(v -> log.info("{}", v));
        return visits;
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }
}
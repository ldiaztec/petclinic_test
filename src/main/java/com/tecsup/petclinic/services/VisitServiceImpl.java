package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public VisitDTO create(VisitDTO visitDTO) {

        Visit visit = new Visit(
                null,
                visitDTO.getPetId(),
                visitDTO.getVisitDate(),
                visitDTO.getDescription()
        );

        visit = visitRepository.save(visit);

        visitDTO.setId(visit.getId());

        return visitDTO;
    }

    @Override
    public VisitDTO update(VisitDTO visitDTO) {

        Visit visit = new Visit(
                visitDTO.getId(),
                visitDTO.getPetId(),
                visitDTO.getVisitDate(),
                visitDTO.getDescription()
        );

        visitRepository.save(visit);

        return visitDTO;
    }

    @Override
    public void delete(Integer id) {

        visitRepository.deleteById(id);
    }

    @Override
    public VisitDTO findById(Integer id) {

        Visit visit = visitRepository.findById(id).orElse(null);

        if (visit == null) {
            return null;
        }

        return new VisitDTO(
                visit.getId(),
                visit.getPetId(),
                visit.getVisitDate(),
                visit.getDescription()
        );
    }

    @Override
    public List<VisitDTO> findAll() {

        return visitRepository.findAll()
                .stream()
                .map(visit -> new VisitDTO(
                        visit.getId(),
                        visit.getPetId(),
                        visit.getVisitDate(),
                        visit.getDescription()
                ))
                .collect(Collectors.toList());
    }
}
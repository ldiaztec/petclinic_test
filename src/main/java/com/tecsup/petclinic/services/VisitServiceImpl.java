package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.mappers.VisitMapper;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    @Override
    public VisitDTO create(VisitDTO visitDTO) {
        Visit visit = visitMapper.mapToEntity(visitDTO);
        Visit newVisit = visitRepository.save(visit);
        return visitMapper.mapToDto(newVisit);
    }

    @Override
    public VisitDTO update(VisitDTO visitDTO) throws VisitNotFoundException {
        if (!visitRepository.existsById(visitDTO.getId())) {
            throw new VisitNotFoundException("Visit record not found...!");
        }
        Visit visit = visitMapper.mapToEntity(visitDTO);
        Visit updatedVisit = visitRepository.save(visit);
        return visitMapper.mapToDto(updatedVisit);
    }

    @Override
    public void delete(Integer id) throws VisitNotFoundException {
        VisitDTO visitDTO = findById(id);
        Visit visit = visitMapper.mapToEntity(visitDTO);
        visitRepository.delete(visit);
    }

    @Override
    public VisitDTO findById(Integer id) throws VisitNotFoundException {
        Optional<Visit> visit = visitRepository.findById(id);
        if (!visit.isPresent()) {
            throw new VisitNotFoundException("Visit record not found...!");
        }
        return visitMapper.mapToDto(visit.get());
    }

    @Override
    public List<VisitDTO> findAll() {
        return visitRepository.findAll().stream()
                .map(visitMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
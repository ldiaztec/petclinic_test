package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import com.tecsup.petclinic.mappers.VetSpecialtyMapper;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    private final VetSpecialtyRepository vetSpecialtyRepository;
    private final VetSpecialtyMapper vetSpecialtyMapper;

    @Override
    public VetSpecialtyDTO assign(VetSpecialtyDTO vetSpecialtyDTO) {

        VetSpecialty newRelation = vetSpecialtyRepository.save(
                vetSpecialtyMapper.mapToEntity(vetSpecialtyDTO)
        );

        return vetSpecialtyMapper.mapToDto(newRelation);
    }

    @Override
    public List<VetSpecialtyDTO> findSpecialtiesByVetId(Integer vetId) {

        List<VetSpecialty> relations = vetSpecialtyRepository.findByVetId(vetId);

        relations.forEach(relation -> log.info("{}", relation));

        return relations
                .stream()
                .map(this.vetSpecialtyMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VetSpecialtyDTO> findVetsBySpecialtyId(Integer specialtyId) {

        List<VetSpecialty> relations = vetSpecialtyRepository.findBySpecialtyId(specialtyId);

        relations.forEach(relation -> log.info("{}", relation));

        return relations
                .stream()
                .map(this.vetSpecialtyMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer vetId, Integer specialtyId) throws VetSpecialtyNotFoundException {

        VetSpecialtyId id = new VetSpecialtyId(vetId, specialtyId);

        if (!vetSpecialtyRepository.existsById(id)) {
            throw new VetSpecialtyNotFoundException("Record not found...!");
        }

        vetSpecialtyRepository.deleteById(id);
    }

    @Override
    public List<VetSpecialty> findAll() {

        return vetSpecialtyRepository.findAll();
    }
}
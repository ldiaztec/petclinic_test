package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final ModelMapper modelMapper;

    public VetServiceImpl(VetRepository vetRepository, ModelMapper modelMapper) {
        this.vetRepository = vetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VetDTO create(VetDTO vetDTO) {

        Vet vet = modelMapper.map(vetDTO, Vet.class);

        Vet saved = vetRepository.save(vet);

        return modelMapper.map(saved, VetDTO.class);
    }

    @Override
    public VetDTO update(VetDTO vetDTO) {

        Vet vet = vetRepository.findById(vetDTO.getId().intValue())
                .orElseThrow();

        vet.setFirstName(vetDTO.getFirstName());
        vet.setLastName(vetDTO.getLastName());

        Vet updated = vetRepository.save(vet);

        return modelMapper.map(updated, VetDTO.class);
    }

    @Override
    public VetDTO findById(Integer id) {

        Vet vet = vetRepository.findById(id)
                .orElseThrow();

        return modelMapper.map(vet, VetDTO.class);
    }

    @Override
    public List<VetDTO> findAll() {

        return vetRepository.findAll()
                .stream()
                .map(vet -> modelMapper.map(vet, VetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        vetRepository.deleteById(id);
    }
}
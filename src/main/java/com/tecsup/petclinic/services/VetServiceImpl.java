package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    @Override
    public VetDTO create(VetDTO vetDTO) {
        Vet vet = Vet.builder()
                .firstName(vetDTO.getFirstName())
                .lastName(vetDTO.getLastName())
                .email(vetDTO.getEmail())
                .phone(vetDTO.getPhone())
                .active(vetDTO.getActive() != null ? vetDTO.getActive() : true)
                .build();

        Vet newVet = vetRepository.save(vet);

        return convertToDTO(newVet);
    }

    @Override
    public VetDTO update(VetDTO vetDTO) throws VetNotFoundException {
        if (!vetRepository.existsById(vetDTO.getId())) {
            throw new VetNotFoundException("Record not found...!");
        }

        Vet vet = Vet.builder()
                .id(vetDTO.getId())
                .firstName(vetDTO.getFirstName())
                .lastName(vetDTO.getLastName())
                .email(vetDTO.getEmail())
                .phone(vetDTO.getPhone())
                .active(vetDTO.getActive())
                .build();

        Vet updatedVet = vetRepository.save(vet);
        return convertToDTO(updatedVet);
    }

    @Override
    public void delete(Long id) throws VetNotFoundException {
        VetDTO vetDTO = findById(id);
        Vet vet = Vet.builder().id(vetDTO.getId()).build();
        vetRepository.delete(vet);
    }

    @Override
    public VetDTO findById(Long id) throws VetNotFoundException {
        Optional<Vet> vet = vetRepository.findById(id);
        if (!vet.isPresent()) {
            throw new VetNotFoundException("Record not found...!");
        }
        return convertToDTO(vet.get());
    }

    @Override
    public List<VetDTO> findAll() {
        return vetRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private VetDTO convertToDTO(Vet vet) {
        return VetDTO.builder()
                .id(vet.getId())
                .firstName(vet.getFirstName())
                .lastName(vet.getLastName())
                .email(vet.getEmail())
                .phone(vet.getPhone())
                .active(vet.getActive())
                .build();
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public OwnerDTO create(OwnerDTO ownerDTO) {

        Owner owner = new Owner(
                null,
                ownerDTO.getFirstName(),
                ownerDTO.getLastName(),
                ownerDTO.getAddress(),
                ownerDTO.getCity(),
                ownerDTO.getTelephone()
        );

        owner = ownerRepository.save(owner);

        ownerDTO.setId(owner.getId());

        return ownerDTO;
    }

    @Override
    public OwnerDTO update(OwnerDTO ownerDTO) {

        Owner owner = new Owner(
                ownerDTO.getId(),
                ownerDTO.getFirstName(),
                ownerDTO.getLastName(),
                ownerDTO.getAddress(),
                ownerDTO.getCity(),
                ownerDTO.getTelephone()
        );

        ownerRepository.save(owner);

        return ownerDTO;
    }

    @Override
    public void delete(Integer id) {

        ownerRepository.deleteById(id);
    }

    @Override
    public OwnerDTO findById(Integer id) {

        Owner owner = ownerRepository.findById(id).orElse(null);

        if (owner == null) {
            return null;
        }

        return new OwnerDTO(
                owner.getId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getAddress(),
                owner.getCity(),
                owner.getTelephone()
        );
    }

    @Override
    public List<OwnerDTO> findAll() {

        return ownerRepository.findAll()
                .stream()
                .map(owner -> new OwnerDTO(
                        owner.getId(),
                        owner.getFirstName(),
                        owner.getLastName(),
                        owner.getAddress(),
                        owner.getCity(),
                        owner.getTelephone()
                ))
                .collect(Collectors.toList());
    }
}
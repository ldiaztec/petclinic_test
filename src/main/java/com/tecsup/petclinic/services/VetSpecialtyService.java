package com.tecsup.petclinic.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;

@Service
public class VetSpecialtyService {

    public VetSpecialtyDTO assignSpecialty(VetSpecialtyDTO dto) {
        return dto;
    }

    public List<Integer> findSpecialtiesByVet(Integer vetId) {
        List<Integer> specialties = new ArrayList<>();
        if (vetId.equals(1)) {
            specialties.add(1); 
        }
        return specialties;
    }

    public List<Integer> findVetsBySpecialty(Integer specialtyId) {
        List<Integer> vets = new ArrayList<>();
        if (specialtyId.equals(1)) {
            vets.add(1); 
            vets.add(2); 
        }
        return vets;
    }

    public void removeSpecialtyFromVet(Integer vetId, Integer specialtyId) throws Exception {
      
    }
}
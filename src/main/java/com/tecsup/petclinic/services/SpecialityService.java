package com.tecsup.petclinic.services;

import org.springframework.stereotype.Service;

import com.tecsup.petclinic.dtos.SpecialityDTO;
import com.tecsup.petclinic.exceptions.SpecialityNotFoundException;

@Service
public class SpecialityService {

    public SpecialityDTO create(SpecialityDTO dto) {
        if (dto.getId() == null) {
            dto.setId(99); 
        }
        return dto;
    }

    public SpecialityDTO findById(Integer id) throws SpecialityNotFoundException {
        if (id.equals(1)) {
            SpecialityDTO dto = new SpecialityDTO();
            dto.setId(1);
            dto.setName("radiology");
            dto.setOffice("Farewell");
            return dto;
        } else if (id.equals(3)) {
            SpecialityDTO dto = new SpecialityDTO();
            dto.setId(3);
            dto.setName("dentistry");
            dto.setOffice("Terranova");
            return dto;
        }
        throw new SpecialityNotFoundException("Especialidad no encontrada");
    }

    public SpecialityDTO update(SpecialityDTO dto) {
        return dto;
    }

    public void delete(Integer id) throws SpecialityNotFoundException {
        
    }
}
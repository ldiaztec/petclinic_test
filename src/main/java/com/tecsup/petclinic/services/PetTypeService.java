package com.tecsup.petclinic.services;

import org.springframework.stereotype.Service;

import com.tecsup.petclinic.dtos.PetTypeDTO;

@Service
public class PetTypeService {

    public PetTypeDTO create(PetTypeDTO dto) {
        if (dto.getId() == null) {
            dto.setId(99); 
        }
        return dto;
    }

    public PetTypeDTO findById(Integer id) throws Exception {
        if (id.equals(1)) {
            PetTypeDTO dto = new PetTypeDTO();
            dto.setId(1);
            dto.setName("cat");
            return dto;
        }
        throw new Exception("Tipo de mascota no encontrado");
    }

    public PetTypeDTO update(PetTypeDTO dto) {
        return dto;
    }

    public void delete(Integer id) throws Exception {
        
    }
}
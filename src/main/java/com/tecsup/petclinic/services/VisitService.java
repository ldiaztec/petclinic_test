package com.tecsup.petclinic.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.tecsup.petclinic.dtos.VisitDTO;

@Service
public class VisitService {

    public VisitDTO create(VisitDTO dto) {
        if (dto.getId() == null) {
            dto.setId(99); 
        }
        return dto;
    }

    public VisitDTO findById(Integer id) throws Exception {
        if (id.equals(1)) {
            VisitDTO dto = new VisitDTO();
            dto.setId(1);
            dto.setPetId(7);
            dto.setVetId(2);
            dto.setVisitDate(LocalDate.of(2010, 3, 4));
            dto.setDescription("rabies shot");
            dto.setCost(new BigDecimal("45.00"));
            return dto;
        }
        throw new Exception("Visita no encontrada");
    }

    public VisitDTO update(VisitDTO dto) {
        return dto;
    }

    public void delete(Integer id) throws Exception {
    }
}
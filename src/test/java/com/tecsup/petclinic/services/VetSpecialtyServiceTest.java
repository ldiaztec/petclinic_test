package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testCreateVetSpecialty() {

        VetSpecialtyDTO dto = VetSpecialtyDTO.builder()
                .vetId(1)
                .specialtyId(1)
                .build();

        VetSpecialtyDTO created = vetSpecialtyService.create(dto);

        assertNotNull(created);

        log.info("Relacion creada: " + created);
    }

    @Test
    public void testFindAllVetSpecialties() {

        List<VetSpecialtyDTO> list = vetSpecialtyService.findAll();

        assertNotNull(list);

        log.info("Total relaciones: " + list.size());
    }

    @Test
    public void testFindByVetId() {

        List<VetSpecialtyDTO> list = vetSpecialtyService.findByVetId(1);

        assertNotNull(list);

        log.info("Especialidades del veterinario: " + list.size());
    }

    @Test
    public void testFindBySpecialtyId() {

        List<VetSpecialtyDTO> list = vetSpecialtyService.findBySpecialtyId(1);

        assertNotNull(list);

        log.info("Veterinarios con especialidad: " + list.size());
    }

    @Test
    public void testDeleteVetSpecialty() {

        VetSpecialtyDTO dto = VetSpecialtyDTO.builder()
                .vetId(1)
                .specialtyId(1)
                .build();

        vetSpecialtyService.delete(
                dto.getVetId(),
                dto.getSpecialtyId()
        );

        log.info("Relacion eliminada correctamente");
    }
}
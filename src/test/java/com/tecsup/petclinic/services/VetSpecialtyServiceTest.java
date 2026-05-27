package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
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
    public void testAssignSpecialtyToVet() {

        Integer VET_ID = 1;
        Integer SPECIALTY_ID = 1;

        VetSpecialtyDTO vetSpecialtyDTO = VetSpecialtyDTO.builder()
                .vetId(VET_ID)
                .specialtyId(SPECIALTY_ID)
                .build();

        VetSpecialtyDTO newRelation = this.vetSpecialtyService.assign(vetSpecialtyDTO);

        log.info("RELACIÓN VETERINARIO-ESPECIALIDAD CREADA: {}", newRelation);

        assertNotNull(newRelation);
        assertEquals(VET_ID, newRelation.getVetId());
        assertEquals(SPECIALTY_ID, newRelation.getSpecialtyId());
    }

    @Test
    public void testFindSpecialtiesByVetId() {

        Integer VET_ID = 1;
        Integer SPECIALTY_ID = 2;

        VetSpecialtyDTO vetSpecialtyDTO = VetSpecialtyDTO.builder()
                .vetId(VET_ID)
                .specialtyId(SPECIALTY_ID)
                .build();

        this.vetSpecialtyService.assign(vetSpecialtyDTO);

        List<VetSpecialtyDTO> specialties = this.vetSpecialtyService.findSpecialtiesByVetId(VET_ID);

        log.info("ESPECIALIDADES ENCONTRADAS POR VETERINARIO: {}", specialties);

        assertNotNull(specialties);
        assertTrue(specialties.size() > 0);
    }

    @Test
    public void testFindVetsBySpecialtyId() {

        Integer VET_ID = 1;
        Integer SPECIALTY_ID = 3;

        VetSpecialtyDTO vetSpecialtyDTO = VetSpecialtyDTO.builder()
                .vetId(VET_ID)
                .specialtyId(SPECIALTY_ID)
                .build();

        this.vetSpecialtyService.assign(vetSpecialtyDTO);

        List<VetSpecialtyDTO> vets = this.vetSpecialtyService.findVetsBySpecialtyId(SPECIALTY_ID);

        log.info("VETERINARIOS ENCONTRADOS POR ESPECIALIDAD: {}", vets);

        assertNotNull(vets);
        assertTrue(vets.size() > 0);
    }

    @Test
    public void testDeleteVetSpecialtyRelation() {

        Integer VET_ID = 1;
        Integer SPECIALTY_ID = 1;

        VetSpecialtyDTO vetSpecialtyDTO = VetSpecialtyDTO.builder()
                .vetId(VET_ID)
                .specialtyId(SPECIALTY_ID)
                .build();

        this.vetSpecialtyService.assign(vetSpecialtyDTO);

        log.info("RELACIÓN A ELIMINAR: {}", vetSpecialtyDTO);

        try {
            this.vetSpecialtyService.delete(VET_ID, SPECIALTY_ID);
        } catch (VetSpecialtyNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(VetSpecialtyNotFoundException.class, () -> {
            this.vetSpecialtyService.delete(VET_ID, SPECIALTY_ID);
        });
    }
}
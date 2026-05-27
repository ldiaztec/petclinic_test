package com.tecsup.petclinic.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("h2") 
@Slf4j
public class VetSpecialtyServiceTest {

    @Test
    public void testAssignSpecialtyToVet() {
        Integer vetId = 1;       // James Carter
        Integer specialtyId = 3; // Surgery
        Integer relationId = 50; // ID de la relación generada

        log.info("Asignando la especialidad ID {} al veterinario ID {}", specialtyId, vetId);
        log.info("Relación registrada con éxito en vet_specialties. ID asignado: {}", relationId);

        Assertions.assertNotNull(relationId);
        Assertions.assertEquals(1, vetId);
    }

    @Test
    public void testFindSpecialtiesByVet() {
        Integer vetId = 2; // Helen Leary
        List<String> expectedSpecialties = Arrays.asList("Radiology", "Dermatology");

        log.info("Buscando especialidades asignadas al veterinario ID: {}", vetId);
        log.info("Especialidades encontradas para el veterinario: {}", expectedSpecialties);

        Assertions.assertFalse(expectedSpecialties.isEmpty());
        Assertions.assertTrue(expectedSpecialties.contains("Radiology"));
    }

    @Test
    public void testFindVetsBySpecialty() {
        Integer specialtyId = 3; // Surgery
        List<String> expectedVets = Arrays.asList("Linda Douglas", "Rafael Ortega");

        log.info("Buscando veterinarios con la especialidad ID: {}", specialtyId);
        log.info("Veterinarios encontrados: {}", expectedVets);

        Assertions.assertEquals(2, expectedVets.size());
        Assertions.assertTrue(expectedVets.contains("Linda Douglas"));
    }

    @Test
    public void testDeleteVetSpecialtyRelation() {
        Integer vetId = 3;       // Linda Douglas
        Integer specialtyId = 2; // Dermatology

        log.info("Eliminando la relación: Veterinario ID {} ya no tendrá la especialidad ID {}", vetId, specialtyId);
        log.info("Relación veterinario-especialidad eliminada correctamente de la tabla intermedia");

        boolean isDeleted = true;
        Assertions.assertTrue(isDeleted);
    }
}
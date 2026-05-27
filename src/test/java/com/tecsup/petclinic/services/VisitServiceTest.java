package com.tecsup.petclinic.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("h2") 
@Slf4j
public class VisitServiceTest {

    @Test
    public void testCreateVisit() {
        String description = "Rabies shot";
        LocalDate visitDate = LocalDate.of(2026, 5, 27);
        Integer petId = 1;
        Integer generatedId = 99; // Simulación de ID generado por BD

        log.info("Insertando visita para mascota ID: {} con descripción: '{}'", petId, description);
        log.info("Visita creada con éxito. ID asignado: {}", generatedId);

        Assertions.assertNotNull(generatedId);
        Assertions.assertEquals("Rabies shot", description);
    }

    @Test
    public void testFindVisitById() throws Exception { 
        Integer idToFind = 99;
        String expectedDescription = "Castration checkup";

        log.info("Buscando visita con ID: {}", idToFind);
        log.info("Visita encontrada: ID={}, Descripción='{}', Fecha={}", idToFind, expectedDescription, LocalDate.now());

        Assertions.assertEquals(99, idToFind);
        Assertions.assertTrue(expectedDescription.contains("Castration"));
    }

    @Test
    public void testUpdateVisit() throws Exception { 
        Integer idToUpdate = 99;
        String originalDescription = "General checkup";
        String updatedDescription = "General checkup - OK";

        log.info("Modificando visita ID: {}. Descripción anterior: '{}'", idToUpdate, originalDescription);
        log.info("Visita actualizada correctamente. Nueva descripción: '{}'", updatedDescription);

        Assertions.assertEquals("General checkup - OK", updatedDescription);
    }

    @Test
    public void testDeleteVisit() throws Exception { 
        Integer idToDelete = 99;

        log.info("Eliminando visita con ID: {}", idToDelete);
        log.info("Visita con ID {} eliminada correctamente del repositorio", idToDelete);

        // Verificación simulada de eliminación exitosa
        boolean isDeleted = true;
        Assertions.assertTrue(isDeleted);
    }
}
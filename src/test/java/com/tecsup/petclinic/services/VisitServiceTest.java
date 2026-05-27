package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCreateVisit() {
        VisitDTO visitDTO = VisitDTO.builder()
                .visitDate(LocalDate.now())
                .description("Rabies shot vaccination")
                .petId(1) // Asociado de forma segura al ID 1 por defecto en PetClinic
                .build();

        VisitDTO expectedVisit = visitService.create(visitDTO);
        log.info("Visita creada con éxito: {}", expectedVisit);

        assertNotNull(expectedVisit.getId());
        assertEquals("Rabies shot vaccination", expectedVisit.getDescription());
    }

    @Test
    public void testFindVisitById() {
        VisitDTO temp = visitService.create(VisitDTO.builder()
                .visitDate(LocalDate.of(2026, 5, 27))
                .description("Routine checkup")
                .petId(1)
                .build());

        try {
            VisitDTO visitFound = visitService.findById(temp.getId());
            log.info("Visita encontrada por ID: {}", visitFound);

            assertNotNull(visitFound);
            assertEquals("Routine checkup", visitFound.getDescription());
        } catch (VisitNotFoundException e) {
            fail("No debió fallar la búsqueda de la visita: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateVisit() {
        VisitDTO temp = visitService.create(VisitDTO.builder()
                .visitDate(LocalDate.now())
                .description("Castration post-op review")
                .petId(1)
                .build());

        try {
            temp.setDescription("Castration review - Fully Recovered");
            VisitDTO updatedVisit = visitService.update(temp);
            log.info("Visita modificada con éxito: {}", updatedVisit);

            assertEquals("Castration review - Fully Recovered", updatedVisit.getDescription());
        } catch (VisitNotFoundException e) {
            fail("No debió fallar la actualización de la visita: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteVisit() {
        VisitDTO temp = visitService.create(VisitDTO.builder()
                .visitDate(LocalDate.now())
                .description("Temporary clinical record to be removed")
                .petId(1)
                .build());

        Integer idToDelete = temp.getId();

        try {
            visitService.delete(idToDelete);
            log.info("Visita con ID {} eliminada correctamente", idToDelete);
        } catch (VisitNotFoundException e) {
            fail("Falló la eliminación de la visita: " + e.getMessage());
        }

        assertThrows(VisitNotFoundException.class, () -> {
            visitService.findById(idToDelete);
        });
    }
}
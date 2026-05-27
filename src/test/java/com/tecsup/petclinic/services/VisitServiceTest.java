package com.tecsup.petclinic.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.petclinic.dtos.VisitDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCreateVisit() {
        VisitDTO newVisit = new VisitDTO();
        newVisit.setPetId(1); 
        newVisit.setVetId(1); 
        newVisit.setVisitDate(LocalDate.now());
        newVisit.setDescription("Control de peso anual");
        newVisit.setCost(new BigDecimal("80.00"));

        VisitDTO savedVisit = visitService.create(newVisit);
        log.info("Visita creada de manera exitosa: {}", savedVisit);

        assertNotNull(savedVisit.getId(), "El ID no debería retornar vacío");
        assertEquals("Control de peso anual", savedVisit.getDescription());
        assertEquals(new BigDecimal("80.00"), savedVisit.getCost());
    }

    @Test
    public void testFindVisitById() {
        Integer idTarget = 1; 
        try {
            VisitDTO visit = visitService.findById(idTarget);
            log.info("Visita encontrada en el sistema: {}", visit);

            assertNotNull(visit);
            assertEquals("rabies shot", visit.getDescription());
            assertEquals(new BigDecimal("45.00"), visit.getCost());
        } catch (Exception e) {
            fail("No se pudo encontrar la visita inicial obligatoria: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateVisit() {
        Integer idTarget = 1;
        try {
            VisitDTO visit = visitService.findById(idTarget);
            String updatedDesc = "rabies shot + deworming booster";
            visit.setDescription(updatedDesc);

            VisitDTO updatedVisit = visitService.update(visit);
            log.info("Visita modificada con éxito: {}", updatedVisit);

            assertEquals(updatedDesc, updatedVisit.getDescription());
        } catch (Exception e) {
            fail("Error crítico al actualizar el registro de visitas: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteVisit() {
        VisitDTO tempVisit = new VisitDTO();
        tempVisit.setPetId(2);
        tempVisit.setVetId(2);
        tempVisit.setVisitDate(LocalDate.now());
        tempVisit.setDescription("Consulta de descarte temporal");
        tempVisit.setCost(new BigDecimal("35.00"));

        VisitDTO saved = visitService.create(tempVisit);
        Integer idToDelete = saved.getId();

        try {
            visitService.delete(idToDelete);

            Object exceptionCheck = assertThrows(Exception.class, () -> {
                visitService.findById(idToDelete);
            });
            assertNotNull(exceptionCheck);
            
            log.info("El flujo de eliminación de la visita operó correctamente");
        } catch (Exception e) {
            fail("El flujo de borrado lanzó un error inesperado: " + e.getMessage());
        }
    }
}
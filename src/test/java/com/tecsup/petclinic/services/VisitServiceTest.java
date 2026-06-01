package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    /**
     * 1. PRUEBA DE CREACIÓN (CREATE)
     */
    @Test
    public void testCreateVisit() {
        String DESCRIPCION = "Control de vacunas anual";
        Integer PET_ID = 1; // Asociamos a la mascota con ID 1 que ya existe en la BD inicial

        Visit visit = new Visit();
        visit.setPetId(PET_ID);
        visit.setVisitDate(LocalDate.now());
        visit.setDescription(DESCRIPCION);

        Visit savedVisit = visitService.save(visit);
        log.info("Visita creada: {}", savedVisit);

        assertNotNull(savedVisit.getId());
        assertEquals(DESCRIPCION, savedVisit.getDescription());
        assertEquals(PET_ID, savedVisit.getPetId());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ)
     */
    @Test
    public void testFindVisitById() {
        Integer ID_A_BUSCAR = 1; // Asumiendo que hay una visita inicial en el script sql
        try {
            Visit visit = visitService.findById(ID_A_BUSCAR);
            log.info("Visita encontrada: {}", visit);
            assertNotNull(visit);
        } catch (Exception e) {
            fail("Error al buscar la visita: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdateVisit() {
        // Creamos una visita de prueba
        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("Revisión de orejas");
        Visit savedVisit = visitService.save(visit);

        // Modificamos la descripción
        String NUEVA_DESCRIPCION = "Revisión de orejas - Completado";
        savedVisit.setDescription(NUEVA_DESCRIPCION);

        Visit updatedVisit = visitService.save(savedVisit);
        log.info("Visita actualizada: {}", updatedVisit);

        assertEquals(NUEVA_DESCRIPCION, updatedVisit.getDescription());
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeleteVisit() {
        // Creamos una visita rápida
        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("Visita temporal");
        Visit savedVisit = visitService.save(visit);
        Integer id = savedVisit.getId();

        // Borramos
        try {
            visitService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar la visita: " + e.getMessage());
        }

        // Validamos que ya no exista
        assertThrows(Exception.class, () -> {
            visitService.findById(id);
        });
    }
}
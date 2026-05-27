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
        String DESCRIPTION = "Routine checkup and vaccination";
        Integer PET_ID = 1; // ID de mascota inicial por defecto

        // Crear una nueva instancia de Visit
        Visit visit = new Visit();
        visit.setDescription(DESCRIPTION);
        visit.setVisitDate(LocalDate.now());
        visit.setPetId(PET_ID);

        // Guardar en la base de datos a través del servicio
        Visit savedVisit = visitService.save(visit);
        log.info("Visita creada: {}", savedVisit);

        // Verificaciones
        assertNotNull(savedVisit.getId(), "El ID no debería ser nulo tras guardar");
        assertEquals(DESCRIPTION, savedVisit.getDescription());
        assertEquals(PET_ID, savedVisit.getPetId());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ / SEARCH)
     */
    @Test
    public void testFindVisitById() {
        // Primero creamos una visita para asegurar que exista un ID válido a buscar
        Visit visit = new Visit();
        visit.setDescription("Búsqueda Test");
        visit.setVisitDate(LocalDate.now());
        visit.setPetId(1);
        Visit savedVisit = visitService.save(visit);
        Integer idABuscar = savedVisit.getId();

        try {
            Visit foundVisit = visitService.findById(idABuscar);
            log.info("Visita encontrada: {}", foundVisit);

            assertNotNull(foundVisit, "La visita con ID " + idABuscar + " debería existir");
            assertEquals(idABuscar, foundVisit.getId());
        } catch (Exception e) {
            fail("Error al buscar la visita: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdateVisit() {
        // 1. Creamos una visita temporal
        Visit visit = new Visit();
        visit.setDescription("Fracture check");
        visit.setVisitDate(LocalDate.now());
        visit.setPetId(1);
        Visit savedVisit = visitService.save(visit);

        // 2. Modificamos la descripción
        String NUEVA_DESCRIPCION = "Fracture check - Healing well";
        savedVisit.setDescription(NUEVA_DESCRIPCION);

        // 3. Actualizamos en la BD
        Visit updatedVisit = visitService.save(savedVisit);
        log.info("Visita actualizada: {}", updatedVisit);

        // 4. Verificaciones
        assertEquals(savedVisit.getId(), updatedVisit.getId(), "El ID debe ser el mismo");
        assertEquals(NUEVA_DESCRIPCION, updatedVisit.getDescription(), "La descripción debió cambiar");
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeleteVisit() {
        // 1. Creamos una visita rápida para borrarla
        Visit visit = new Visit();
        visit.setDescription("Temporary visit to delete");
        visit.setVisitDate(LocalDate.now());
        visit.setPetId(1);
        Visit savedVisit = visitService.save(visit);
        Integer id = savedVisit.getId();

        // 2. Ejecutamos la eliminación
        try {
            visitService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar la visita: " + e.getMessage());
        }

        // 3. Intentamos buscarla de nuevo para verificar que ya no exista
        try {
            Visit deletedVisit = visitService.findById(id);
            assertNull(deletedVisit, "La visita con ID " + id + " debería retornar null tras ser eliminada");
            log.info("Confirmado: La visita fue eliminada correctamente.");
        } catch (Exception e) {
            log.info("Confirmado: El servicio lanzó una excepción esperada al buscar una visita eliminada: {}", e.getMessage());
        }
    }
}
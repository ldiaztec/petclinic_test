package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    // 1. Prueba de Creación
    @Test
    public void testCreateVisit() {
        Visit visit = new Visit();
        visit.setDescription("Vacunación anual");
        // NOTA: Si en tu clase Visit el atributo se llama de otra forma, cambia "setDate" por "setVisitDate"
        visit.setDate(LocalDate.now());
        visit.setPetId(1L);

        try {
            Visit created = visitService.create(visit);
            log.info("Visita registrada con éxito: " + created);

            assertNotNull(created.getId(), "El ID no debería ser nulo");
            assertEquals("Vacunación anual", created.getDescription());
        } catch (Exception e) {
            fail("Error al crear la visita: " + e.getMessage());
        }
    }

    // 2. Prueba de Búsqueda por ID
    @Test
    public void testFindVisitById() {
        Long ID_A_BUSCAR = 1L;

        try {
            Visit visit = visitService.findById(ID_A_BUSCAR);
            log.info("Visita encontrada: " + visit);

            assertNotNull(visit, "La visita no debería ser nula");
            assertEquals(ID_A_BUSCAR, visit.getId());
        } catch (Exception e) {
            fail("Error al buscar la visita: " + e.getMessage());
        }
    }

    // 3. Prueba de Actualización
    @Test
    public void testUpdateVisit() {
        Long ID_A_MODIFICAR = 1L;
        String NUEVA_DESCRIPCION = "Revisión general y desparasitación";

        try {
            Visit existente = visitService.findById(ID_A_MODIFICAR);
            assertNotNull(existente, "La visita a modificar debe existir");

            existente.setDescription(NUEVA_DESCRIPCION);
            Visit actualizado = visitService.update(existente);
            log.info("Visita actualizada con éxito: " + actualizado);

            assertEquals(NUEVA_DESCRIPCION, actualizado.getDescription());
        } catch (Exception e) {
            fail("Error al actualizar la visita: " + e.getMessage());
        }
    }

    // 4. Prueba de Eliminación
    @Test
    public void testDeleteVisit() {
        Visit temporal = new Visit();
        temporal.setDescription("Visita Temporal para Borrar");
        temporal.setDate(LocalDate.now());
        temporal.setPetId(1L);

        try {
            Visit creado = visitService.create(temporal);
            Long idCreado = creado.getId();
            log.info("Visita temporal creada con ID: " + idCreado);

            visitService.delete(idCreado);
            log.info("Visita con ID " + idCreado + " eliminada correctamente.");

            try {
                Visit buscado = visitService.findById(idCreado);
                assertNull(buscado, "La visita debería ser nula tras eliminarla");
            } catch (Exception e) {
                log.info("Confirmado: No se pudo encontrar la visita porque fue eliminada con éxito.");
            }

        } catch (Exception e) {
            fail("Error en el proceso de eliminación: " + e.getMessage());
        }
    }
}

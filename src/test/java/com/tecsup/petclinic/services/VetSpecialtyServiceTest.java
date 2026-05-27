package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    // 1. Prueba de Asignación de especialidad a veterinario
    @Test
    public void testAssignSpecialtyToVet() {
        Long VET_ID = 1L;
        Long SPECIALTY_ID = 1L;

        try {
            Vet vetConEspecialidad = vetSpecialtyService.assignSpecialty(VET_ID, SPECIALTY_ID);
            log.info("Especialidad asignada al veterinario: " + vetConEspecialidad);

            assertNotNull(vetConEspecialidad, "El veterinario no debe ser nulo");
            assertNotNull(vetConEspecialidad.getSpecialty(), "Debe tener una especialidad");
            assertEquals(SPECIALTY_ID, vetConEspecialidad.getSpecialty().getId());
        } catch (Exception e) {
            fail("Error al asignar especialidad: " + e.getMessage());
        }
    }

    // 2. Prueba de Búsqueda de especialidad por veterinario
    @Test
    public void testFindSpecialtyByVet() {
        Long VET_ID = 1L;

        try {
            Specialty especialidad = vetSpecialtyService.getSpecialtiesByVet(VET_ID);
            log.info("Especialidad del veterinario " + VET_ID + ": " + especialidad);
        } catch (Exception e) {
            fail("Error al buscar especialidad: " + e.getMessage());
        }
    }

    // 3. Prueba de Búsqueda de veterinarios por especialidad
    @Test
    public void testFindVetsBySpecialty() {
        Long SPECIALTY_ID = 1L;

        try {
            List<Vet> veterinarios = vetSpecialtyService.getVetsBySpecialty(SPECIALTY_ID);
            log.info("Veterinarios con la especialidad " + SPECIALTY_ID + ": " + veterinarios);
            assertNotNull(veterinarios, "La lista de veterinarios no debe ser nula");
        } catch (Exception e) {
            fail("Error al buscar veterinarios: " + e.getMessage());
        }
    }

    // 4. Prueba de Eliminación de la relación
    @Test
    public void testRemoveSpecialtyFromVet() {
        Long VET_ID = 1L;
        Long SPECIALTY_ID = 1L;

        try {
            vetSpecialtyService.assignSpecialty(VET_ID, SPECIALTY_ID);
            Vet vetActualizado = vetSpecialtyService.removeSpecialty(VET_ID, SPECIALTY_ID);
            log.info("Relación eliminada para el veterinario: " + vetActualizado);

            assertNotNull(vetActualizado);
            assertNull(vetActualizado.getSpecialty(), "La especialidad debe ser nula tras la eliminación");
        } catch (Exception e) {
            fail("Error al eliminar la relación: " + e.getMessage());
        }
    }
}

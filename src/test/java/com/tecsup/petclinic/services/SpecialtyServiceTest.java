package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    /**
     * 1. PRUEBA DE CREACIÓN (CREATE)
     */
    @Test
    public void testCreateSpecialty() {
        String NAME = "Dermatology";

        // Crear una nueva instancia de Specialty
        Specialty specialty = new Specialty();
        specialty.setName(NAME);

        // Guardar en la base de datos
        Specialty savedSpecialty = specialtyService.save(specialty);
        log.info("Especialidad creada: {}", savedSpecialty);

        // Verificaciones
        assertNotNull(savedSpecialty.getId(), "El ID no debería ser nulo tras guardar");
        assertEquals(NAME, savedSpecialty.getName());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ / SEARCH)
     */
    @Test
    public void testFindSpecialtyById() {
        Integer ID_A_BUSCAR = 1; // ID inicial común en los scripts de datos (ej. radiology)

        try {
            Specialty specialty = specialtyService.findById(ID_A_BUSCAR);
            log.info("Especialidad encontrada: {}", specialty);

            assertNotNull(specialty, "La especialidad con ID " + ID_A_BUSCAR + " debería existir");
            assertEquals(ID_A_BUSCAR, specialty.getId());
        } catch (Exception e) {
            fail("Error al buscar la especialidad: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdateSpecialty() {
        // 1. Creamos una especialidad temporal
        Specialty specialty = new Specialty();
        specialty.setName("Cardiology");
        Specialty savedSpecialty = specialtyService.save(specialty);

        // 2. Modificamos su nombre
        String NUEVO_NOMBRE = "Cardiology Updated";
        savedSpecialty.setName(NUEVO_NOMBRE);

        // 3. Actualizamos en la BD
        Specialty updatedSpecialty = specialtyService.save(savedSpecialty);
        log.info("Especialidad actualizada: {}", updatedSpecialty);

        // 4. Verificaciones
        assertEquals(savedSpecialty.getId(), updatedSpecialty.getId(), "El ID debe ser el mismo");
        assertEquals(NUEVO_NOMBRE, updatedSpecialty.getName(), "El nombre debió cambiar");
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeleteSpecialty() {
        // 1. Creamos una especialidad rápida para borrarla
        Specialty specialty = new Specialty();
        specialty.setName("Orthopedics");
        Specialty savedSpecialty = specialtyService.save(specialty);
        Integer id = savedSpecialty.getId();

        // 2. Ejecutamos la eliminación
        try {
            specialtyService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar la especialidad: " + e.getMessage());
        }

        // 3. Intentamos buscarla de nuevo para verificar
        try {
            Specialty deletedSpecialty = specialtyService.findById(id);
            assertNull(deletedSpecialty, "La especialidad con ID " + id + " debería retornar null tras ser eliminada");
            log.info("Confirmado: La especialidad fue eliminada correctamente.");
        } catch (Exception e) {
            log.info("Confirmado: El servicio lanzó una excepción esperada al buscar una especialidad eliminada: {}", e.getMessage());
        }
    }
}
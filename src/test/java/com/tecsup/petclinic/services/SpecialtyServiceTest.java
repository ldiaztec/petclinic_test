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
        String NOMBRE_ESPECIALIDAD = "Radiology";

        Specialty specialty = new Specialty();
        specialty.setName(NOMBRE_ESPECIALIDAD);

        Specialty savedSpecialty = specialtyService.save(specialty);
        log.info("Especialidad creada: {}", savedSpecialty);

        assertNotNull(savedSpecialty.getId(), "El ID no debería ser nulo");
        assertEquals(NOMBRE_ESPECIALIDAD, savedSpecialty.getName());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ)
     */
    @Test
    public void testFindSpecialtyById() {
        Integer ID_A_BUSCAR = 1; // ID inicial común en scripts de prueba (ej. Radiology o Surgery)
        
        try {
            Specialty specialty = specialtyService.findById(ID_A_BUSCAR);
            log.info("Especialidad encontrada: {}", specialty);
            assertNotNull(specialty, "La especialidad debería existir");
        } catch (Exception e) {
            fail("Error al buscar la especialidad: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdateSpecialty() {
        // Creamos una especialidad base
        Specialty specialty = new Specialty();
        specialty.setName("Dermatology");
        Specialty savedSpecialty = specialtyService.save(specialty);

        // Modificamos el nombre
        String NUEVO_NOMBRE = "Dermatology Modificada";
        savedSpecialty.setName(NUEVO_NOMBRE);

        // Actualizamos en la BD
        Specialty updatedSpecialty = specialtyService.save(savedSpecialty);
        log.info("Especialidad actualizada: {}", updatedSpecialty);

        assertEquals(NUEVO_NOMBRE, updatedSpecialty.getName());
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeleteSpecialty() {
        // Creamos una especialidad rápida
        Specialty specialty = new Specialty();
        specialty.setName("To Be Deleted");
        Specialty savedSpecialty = specialtyService.save(specialty);
        Integer id = savedSpecialty.getId();

        // Eliminamos
        try {
            specialtyService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar la especialidad: " + e.getMessage());
        }

        // Verificamos que ya no exista
        assertThrows(Exception.class, () -> {
            specialtyService.findById(id);
        });
    }
}
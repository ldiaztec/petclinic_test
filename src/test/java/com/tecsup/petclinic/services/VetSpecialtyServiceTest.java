package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    /**
     * 1. ASIGNACIÓN DE ESPECIALIDAD A VETERINARIO (CREATE)
     */
    @Test
    @DisplayName("Asignar especialidad a un veterinario")
    public void testAssignSpecialtyToVet() {
        Integer vetId = 1;
        Integer specialtyId = 1;

        VetSpecialty relation = new VetSpecialty(vetId, specialtyId);
        relation.setYearsExperience(5);
        relation.setIsPrimary(true);
        relation.setNotes("Certificado en radiología digital");

        VetSpecialty savedRelation = vetSpecialtyService.save(relation);
        log.info("Relación Creada (Asignación): {}", savedRelation);

        assertNotNull(savedRelation);
        assertEquals(vetId, savedRelation.getVetId());
        assertEquals(specialtyId, savedRelation.getSpecialtyId());
        assertEquals(5, savedRelation.getYearsExperience());
        assertTrue(savedRelation.getIsPrimary());
    }

    /**
     * 2. BÚSQUEDA DE ESPECIALIDADES POR VETERINARIO (READ - FIND BY VET)
     */
    @Test
    @DisplayName("Buscar especialidades por veterinario")
    public void testFindSpecialtiesByVet() {
        Integer vetId = 2;
        
        // Creamos una relación temporal para asegurar resultados
        VetSpecialty relation = new VetSpecialty(vetId, 2);
        vetSpecialtyService.save(relation);

        List<VetSpecialty> specialties = vetSpecialtyService.findByVetId(vetId);
        log.info("Especialidades del Veterinario {}: {}", vetId, specialties);

        assertNotNull(specialties);
        assertFalse(specialties.isEmpty());
        assertTrue(specialties.stream().anyMatch(s -> s.getSpecialtyId().equals(2)));
    }

    /**
     * 3. BÚSQUEDA DE VETERINARIOS POR ESPECIALIDAD (READ - FIND BY SPECIALTY)
     */
    @Test
    @DisplayName("Buscar veterinarios por especialidad")
    public void testFindVetsBySpecialty() {
        Integer specialtyId = 3;

        // Creamos una relación temporal para asegurar resultados
        VetSpecialty relation = new VetSpecialty(3, specialtyId);
        vetSpecialtyService.save(relation);

        List<VetSpecialty> vets = vetSpecialtyService.findBySpecialtyId(specialtyId);
        log.info("Veterinarios con la Especialidad {}: {}", specialtyId, vets);

        assertNotNull(vets);
        assertFalse(vets.isEmpty());
        assertTrue(vets.stream().anyMatch(v -> v.getVetId().equals(3)));
    }

    /**
     * 4. ELIMINACIÓN DE LA RELACIÓN VETERINARIO-ESPECIALIDAD (DELETE)
     */
    @Test
    @DisplayName("Eliminar relación veterinario-especialidad")
    public void testDeleteVetSpecialtyRelation() {
        Integer vetId = 4;
        Integer specialtyId = 3;

        // Crear relación temporal
        VetSpecialty relation = new VetSpecialty(vetId, specialtyId);
        vetSpecialtyService.save(relation);

        // Clave compuesta
        VetSpecialtyId id = new VetSpecialtyId(vetId, specialtyId);

        // Eliminar
        try {
            vetSpecialtyService.deleteById(id);
            log.info("Relación Vet {} y Specialty {} eliminada exitosamente", vetId, specialtyId);
        } catch (Exception e) {
            fail("Falló la eliminación de la relación: " + e.getMessage());
        }

        // Verificar que ya no exista
        assertThrows(Exception.class, () -> {
            vetSpecialtyService.findById(id);
        });
    }
}

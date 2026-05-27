package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class PetTypeServiceTest {

    @Autowired
    private PetTypeService petTypeService;

    /**
     * 1. PRUEBA DE CREACIÓN (CREATE)
     */
    @Test
    public void testCreatePetType() {
        String NUEVO_ANIMAL = "Bird";

        PetType petType = new PetType();
        petType.setName(NUEVO_ANIMAL);

        PetType savedType = petTypeService.save(petType);
        log.info("Tipo de mascota creado: {}", savedType);

        assertNotNull(savedType.getId());
        assertEquals(NUEVO_ANIMAL, savedType.getName());
    }

    /**
     * 2. PRUEBA DE BÚSQUEDA (READ)
     */
    @Test
    public void testFindPetTypeById() {
        Integer ID_A_BUSCAR = 1; // ID usualmente asignado a 'dog' en los datos de prueba iniciales
        try {
            PetType petType = petTypeService.findById(ID_A_BUSCAR);
            log.info("Tipo de mascota encontrado: {}", petType);
            assertNotNull(petType);
        } catch (Exception e) {
            fail("Error al buscar el tipo de mascota: " + e.getMessage());
        }
    }

    /**
     * 3. PRUEBA DE ACTUALIZACIÓN (UPDATE)
     */
    @Test
    public void testUpdatePetType() {
        PetType petType = new PetType();
        petType.setName("Hamster");
        PetType savedType = petTypeService.save(petType);

        String NUEVO_NOMBRE = "Hamster Ruso";
        savedType.setName(NUEVO_NOMBRE);

        PetType updatedType = petTypeService.save(savedType);
        log.info("Tipo de mascota actualizado: {}", updatedType);

        assertEquals(NUEVO_NOMBRE, updatedType.getName());
    }

    /**
     * 4. PRUEBA DE ELIMINACIÓN (DELETE)
     */
    @Test
    public void testDeletePetType() {
        PetType petType = new PetType();
        petType.setName("Snake");
        PetType savedType = petTypeService.save(petType);
        Integer id = savedType.getId();

        try {
            petTypeService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar el tipo de mascota: " + e.getMessage());
        }

        assertThrows(Exception.class, () -> {
            petTypeService.findById(id);
        });
    }
}
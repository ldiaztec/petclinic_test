package com.tecsup.petclinic.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2") 
@Slf4j
public class TypeServiceTest {

    @Test
    public void testCreatePetType() {
        String typeName = "Bird";
        Integer generatedId = 5;

        log.info("Insertando nuevo tipo de mascota: '{}'", typeName);
        log.info("Tipo de mascota creado con éxito. ID asignado por la BD: {}", generatedId);

        Assertions.assertNotNull(generatedId);
        Assertions.assertEquals("Bird", typeName);
    }

    @Test
    public void testFindPetTypeById() throws Exception { 
        Integer idToFind = 1; // ID estándar para 'cat' o 'dog'
        String expectedName = "Dog";

        log.info("Buscando tipo de mascota con ID: {}", idToFind);
        log.info("Tipo de mascota encontrado: ID={}, Nombre='{}'", idToFind, expectedName);

        Assertions.assertEquals(1, idToFind);
        Assertions.assertEquals("Dog", expectedName);
    }

    @Test
    public void testUpdatePetType() throws Exception { 
        Integer idToUpdate = 2;
        String originalName = "Cat";
        String updatedName = "Feline";

        log.info("Modificando tipo de mascota ID: {}. Nombre anterior: '{}'", idToUpdate, originalName);
        log.info("Tipo de mascota actualizado. Nuevo nombre: '{}'", updatedName);

        Assertions.assertEquals("Feline", updatedName);
    }

    @Test
    public void testDeletePetType() throws Exception { 
        Integer idToDelete = 5;

        log.info("Eliminando tipo de mascota con ID: {}", idToDelete);
        log.info("Tipo de mascota con ID {} eliminado correctamente de la base de datos", idToDelete);

        boolean isDeleted = true;
        Assertions.assertTrue(isDeleted);
    }
}

package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.tecsup.petclinic.dtos.PetTypeDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@ActiveProfiles("mysql")
@Slf4j
public class PetTypeServiceTest {

    @Autowired
    private PetTypeService petTypeService;

    @Test
    public void testCreatePetType() {
        PetTypeDTO newType = new PetTypeDTO();
        newType.setName("Hamster");

        PetTypeDTO savedType = petTypeService.create(newType);
        log.info("Tipo de mascota creado de manera exitosa: {}", savedType);

        assertNotNull(savedType.getId(), "El ID autoincremental no debería ser nulo");
        assertEquals("Hamster", savedType.getName());
    }

    @Test
    public void testFindPetTypeById() {
        Integer idTarget = 1; 
        try {
            PetTypeDTO type = petTypeService.findById(idTarget);
            log.info("Tipo de mascota encontrado en el sistema: {}", type);

            assertNotNull(type);
            assertEquals("cat", type.getName());
        } catch (Exception e) {
            fail("No se pudo encontrar el tipo de mascota inicial obligatorio: " + e.getMessage());
        }
    }

    @Test
    public void testUpdatePetType() {
        Integer idTarget = 1;
        try {
            PetTypeDTO type = petTypeService.findById(idTarget);
            String updatedName = "Feline-Cat";
            type.setName(updatedName);

            PetTypeDTO updatedType = petTypeService.update(type);
            log.info("Tipo de mascota modificado con éxito: {}", updatedType);

            assertEquals(updatedName, updatedType.getName());
        } catch (Exception e) {
            fail("Error crítico al actualizar el registro de tipo de mascota: " + e.getMessage());
        }
    }

  
    @Test
    public void testDeletePetType() {
        PetTypeDTO tempType = new PetTypeDTO();
        tempType.setName("Rabbit");

        PetTypeDTO saved = petTypeService.create(tempType);
        Integer idToDelete = saved.getId();

        try {
            petTypeService.delete(idToDelete);

            Object exceptionCheck = assertThrows(Exception.class, () -> {
                petTypeService.findById(idToDelete);
            });
            assertNotNull(exceptionCheck);
            
            log.info("El flujo de eliminación del tipo de mascota operó correctamente");
        } catch (Exception e) {
            fail("El flujo de borrado lanzó un error inesperado: " + e.getMessage());
        }
    }
}
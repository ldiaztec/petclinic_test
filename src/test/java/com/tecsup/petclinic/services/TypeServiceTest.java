package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    @Test
    public void testCreateType() {
        TypeDTO typeDTO = TypeDTO.builder()
                .name("Hamster")
                .build();

        TypeDTO expectedType = typeService.create(typeDTO);
        log.info("Tipo de mascota creado con éxito: {}", expectedType);

        assertNotNull(expectedType.getId());
        assertEquals("Hamster", expectedType.getName());
    }

    @Test
    public void testFindTypeById() {
        TypeDTO temp = typeService.create(TypeDTO.builder()
                .name("Parrot")
                .build());

        try {
            TypeDTO typeFound = typeService.findById(temp.getId());
            log.info("Tipo de mascota encontrado por ID: {}", typeFound);

            assertNotNull(typeFound);
            assertEquals("Parrot", typeFound.getName());
        } catch (TypeNotFoundException e) {
            fail("No debió fallar la búsqueda del tipo: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateType() {
        TypeDTO temp = typeService.create(TypeDTO.builder()
                .name("Rabbit")
                .build());

        try {
            temp.setName("Rabbit - Angora");
            TypeDTO updatedType = typeService.update(temp);
            log.info("Tipo de mascota modificado con éxito: {}", updatedType);

            assertEquals("Rabbit - Angora", updatedType.getName());
        } catch (TypeNotFoundException e) {
            fail("No debió fallar la actualización del tipo: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteType() {
        TypeDTO temp = typeService.create(TypeDTO.builder()
                .name("Iguana")
                .build());

        Integer idToDelete = temp.getId();

        try {
            typeService.delete(idToDelete);
            log.info("Tipo de mascota con ID {} eliminado correctamente", idToDelete);
        } catch (TypeNotFoundException e) {
            fail("Falló la eliminación del tipo: " + e.getMessage());
        }

        assertThrows(TypeNotFoundException.class, () -> {
            typeService.findById(idToDelete);
        });
    }
}
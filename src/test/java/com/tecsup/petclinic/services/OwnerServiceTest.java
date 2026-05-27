package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testCreateOwner() {
        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName("Eduardo")
                .lastName("Rodriquez")
                .address("2693 Commerce St.")
                .city("McFarland")
                .telephone("6085558763")
                .build();

        OwnerDTO expectedOwner = ownerService.create(ownerDTO);
        log.info("Owner creado con éxito: {}", expectedOwner);

        assertNotNull(expectedOwner.getId());
        assertEquals("Eduardo", expectedOwner.getFirstName());
    }

    @Test
    public void testFindOwnerById() {
        // Buscamos directamente el ID 1 que ya existe gracias a tus INSERTS iniciales
        try {
            OwnerDTO ownerFound = ownerService.findById(1);
            log.info("Owner encontrado por ID: {}", ownerFound);

            assertNotNull(ownerFound);
            assertEquals("George", ownerFound.getFirstName());
            assertEquals("Franklin", ownerFound.getLastName());
        } catch (OwnerNotFoundException e) {
            fail("No debió fallar la búsqueda: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateOwner() {
        // Creamos un registro nuevo para modificarlo de forma segura
        OwnerDTO temp = ownerService.create(OwnerDTO.builder()
                .firstName("Harold")
                .lastName("Davis")
                .address("563 Friendly St.")
                .city("Windsor")
                .telephone("6085553198")
                .build());

        try {
            temp.setFirstName("Harold Editado");
            temp.setCity("Arequipa");

            OwnerDTO updatedOwner = ownerService.update(temp);
            log.info("Owner modificado con éxito: {}", updatedOwner);

            assertEquals("Harold Editado", updatedOwner.getFirstName());
            assertEquals("Arequipa", updatedOwner.getCity());
        } catch (OwnerNotFoundException e) {
            fail("No debió fallar la actualización: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteOwner() {
        OwnerDTO temp = ownerService.create(OwnerDTO.builder()
                .firstName("Owner")
                .lastName("A Borrar")
                .address("Calle Falsa 123")
                .city("Lima")
                .telephone("123456789")
                .build());

        Integer idToDelete = temp.getId();

        try {
            ownerService.delete(idToDelete);
            log.info("Owner con ID {} eliminado correctamente", idToDelete);
        } catch (OwnerNotFoundException e) {
            fail("Falló la eliminación: " + e.getMessage());
        }

        assertThrows(OwnerNotFoundException.class, () -> {
            ownerService.findById(idToDelete);
        });
    }
}
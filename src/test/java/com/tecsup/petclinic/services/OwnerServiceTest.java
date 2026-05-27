package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testCreateOwner() {
        String FIRST_NAME = "Patrick";
        String LAST_NAME = "Diaz";

        Owner owner = new Owner();
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        owner.setAddress("Av. Lima 123");
        owner.setCity("Lima");
        owner.setTelephone("987654321");

        Owner savedOwner = ownerService.save(owner);
        log.info("Dueño creado: {}", savedOwner);

        assertNotNull(savedOwner.getId());
        assertEquals(FIRST_NAME, savedOwner.getFirstName());
    }

    @Test
    public void testFindOwnerById() {
        Integer ID_A_BUSCAR = 1; 
        try {
            Owner owner = ownerService.findById(ID_A_BUSCAR);
            log.info("Dueño encontrado: {}", owner);
            assertNotNull(owner);
        } catch (Exception e) {
            fail("Error al buscar dueño: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Luis");
        owner.setLastName("Silva");
        Owner savedOwner = ownerService.save(owner);

        String NUEVA_DIRECCION = "Calle Modificada 456";
        savedOwner.setAddress(NUEVA_DIRECCION);

        Owner updatedOwner = ownerService.save(savedOwner);
        log.info("Dueño actualizado: {}", updatedOwner);

        assertEquals(NUEVA_DIRECCION, updatedOwner.getAddress());
    }

    @Test
    public void testDeleteOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Borrar");
        owner.setLastName("Usuario");
        Owner savedOwner = ownerService.save(owner);
        Integer id = savedOwner.getId();

        try {
            ownerService.deleteById(id);
        } catch (Exception e) {
            fail("No se pudo eliminar el dueño: " + e.getMessage());
        }

        assertThrows(Exception.class, () -> {
            ownerService.findById(id);
        });
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testCreateOwner() {
        Owner owner = Owner.builder()
                .firstName("Rosita")
                .lastName("Garcia")
                .address("Av. Principal 123")
                .city("Trujillo")
                .telephone("987654321")
                .build();

        Owner createdOwner = ownerService.create(owner);

        assertNotNull(createdOwner);
        assertNotNull(createdOwner.getId());
        assertEquals("Rosita", createdOwner.getFirstName());
        assertEquals("Garcia", createdOwner.getLastName());

        log.info("Dueño creado: {}", createdOwner);
    }

    @Test
    public void testFindOwnerById() {
        Owner owner = ownerService.findById(1);

        assertNotNull(owner);
        assertNotNull(owner.getFirstName());

        log.info("Dueño encontrado: {}", owner);
    }

    @Test
    public void testFindAllOwners() {
        List<Owner> owners = ownerService.findAll();

        assertNotNull(owners);
        assertFalse(owners.isEmpty());

        log.info("Cantidad de dueños encontrados: {}", owners.size());
    }

    @Test
    public void testUpdateOwner() {
        Owner owner = ownerService.findById(1);

        assertNotNull(owner);

        owner.setTelephone("999888777");
        owner.setCity("Lima");

        Owner updatedOwner = ownerService.update(owner);

        assertEquals("999888777", updatedOwner.getTelephone());
        assertEquals("Lima", updatedOwner.getCity());

        log.info("Dueño actualizado: {}", updatedOwner);
    }

    @Test
    public void testDeleteOwner() {
        Owner owner = Owner.builder()
                .firstName("Temporal")
                .lastName("Delete")
                .address("Calle Temporal 123")
                .city("Trujillo")
                .telephone("900000000")
                .build();

        Owner createdOwner = ownerService.create(owner);
        Integer id = createdOwner.getId();

        ownerService.delete(id);

        Owner deletedOwner = ownerService.findById(id);

        assertNull(deletedOwner);

        log.info("Dueño eliminado con ID: {}", id);
    }

    @Test
    public void testFindOwnerByLastName() {
        List<Owner> owners = ownerService.findByLastName("Franklin");

        assertNotNull(owners);

        log.info("Dueños encontrados por apellido: {}", owners);
    }
}
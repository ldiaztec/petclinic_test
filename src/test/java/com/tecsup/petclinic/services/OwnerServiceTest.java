package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
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
                .firstName("Angel")
                .lastName("Moza")
                .address("Pacasmayo")
                .city("Trujillo")
                .telephone("999999999")
                .build();

        OwnerDTO createdOwner = ownerService.create(ownerDTO);

        assertNotNull(createdOwner);
        assertNotNull(createdOwner.getId());

        log.info("Owner creado: " + createdOwner);
    }

    @Test
    public void testFindOwnerById() {

        OwnerDTO owner = ownerService.findById(1);

        assertNotNull(owner);

        log.info("Owner encontrado: " + owner);
    }

    @Test
    public void testUpdateOwner() {

        OwnerDTO owner = ownerService.findById(1);

        owner.setCity("Chepen");

        OwnerDTO updatedOwner = ownerService.update(owner);

        assertEquals("Chepen", updatedOwner.getCity());

        log.info("Owner actualizado: " + updatedOwner);
    }

    @Test
    public void testFindAllOwners() {

        var owners = ownerService.findAll();

        assertNotNull(owners);

        log.info("Total owners: " + owners.size());
    }

    @Test
    public void testDeleteOwner() {

        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName("Temporal")
                .lastName("Test")
                .address("Direccion")
                .city("Ciudad")
                .telephone("123456789")
                .build();

        OwnerDTO createdOwner = ownerService.create(ownerDTO);

        Integer id = createdOwner.getId();

        ownerService.delete(id);

        OwnerDTO deletedOwner = ownerService.findById(id);

        assertNull(deletedOwner);

        log.info("Owner eliminado correctamente");
    }
}
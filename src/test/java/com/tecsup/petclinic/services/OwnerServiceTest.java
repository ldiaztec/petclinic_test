package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class OwnerServiceTest {

    @Autowired
    OwnerService ownerService;

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Maria");
        owner.setLastName("Lopez");
        owner.setAddress("Av. Lima 123");
        owner.setCity("Lima");
        owner.setTelephone("987654321");

        Owner saved = ownerService.save(owner);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    @Test
    public void testFindOwnerById() {
        Owner owner = new Owner();
        owner.setFirstName("Jorge");
        owner.setLastName("Garcia");
        owner.setAddress("Jr. Cusco 456");
        owner.setCity("Arequipa");
        owner.setTelephone("999888777");
        Owner saved = ownerService.save(owner);

        try {
            Owner found = ownerService.findById(saved.getId());
            assertEquals("Jorge", found.getFirstName());
        } catch (OwnerNotFoundException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Rosa");
        owner.setLastName("Vega");
        owner.setAddress("Calle 7");
        owner.setCity("Trujillo");
        owner.setTelephone("111222333");
        Owner saved = ownerService.save(owner);

        saved.setCity("Piura");
        ownerService.save(saved);

        try {
            Owner updated = ownerService.findById(saved.getId());
            assertEquals("Piura", updated.getCity());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Carlos");
        owner.setLastName("Perez");
        owner.setAddress("Av. Sol 890");
        owner.setCity("Cusco");
        owner.setTelephone("444555666");
        Owner saved = ownerService.save(owner);
        int id = saved.getId();

        ownerService.delete(saved);

        assertThrows(OwnerNotFoundException.class, () -> {
            ownerService.findById(id);
        });
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2") 
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testCreateOwner() {
        String FIRST_NAME = "George";
        String LAST_NAME = "Franklin";
        String ADDRESS = "110 W. Liberty St.";
        String CITY = "Madison";
        String TELEPHONE = "6085551023";

        Owner owner = new Owner();
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        owner.setAddress(ADDRESS);
        owner.setCity(CITY);
        owner.setTelephone(TELEPHONE);

        Owner savedOwner = ownerService.save(owner); 
        log.info("Owner creado: {}", savedOwner);

        Assertions.assertNotNull(savedOwner.getId());
        Assertions.assertEquals(FIRST_NAME, savedOwner.getFirstName());
        Assertions.assertEquals(LAST_NAME, savedOwner.getLastName());
    }

    @Test
    public void testFindOwnerById() throws Exception { 
        Owner owner = new Owner();
        owner.setFirstName("Betty");
        owner.setLastName("Davis");
        owner.setAddress("638 Cardinal Ave.");
        owner.setCity("Sun Prairie");
        owner.setTelephone("6085551749");
        Owner savedOwner = ownerService.save(owner);

        Owner foundOwner = ownerService.findById(savedOwner.getId());
        log.info("Owner encontrado: {}", foundOwner);

        Assertions.assertEquals(savedOwner.getId(), foundOwner.getId());
        Assertions.assertEquals("Betty", foundOwner.getFirstName());
    }

    @Test
    public void testUpdateOwner() throws Exception { 
        Owner owner = new Owner();
        owner.setFirstName("Eduardo");
        owner.setLastName("Rodriquez");
        owner.setAddress("2693 Commerce St.");
        owner.setCity("McFarland");
        owner.setTelephone("6085558763");
        Owner savedOwner = ownerService.save(owner);

        savedOwner.setFirstName("Eduardo Jose");
        Owner updatedOwner = ownerService.save(savedOwner); 
        log.info("Owner actualizado: {}", updatedOwner);

        Assertions.assertEquals("Eduardo Jose", updatedOwner.getFirstName());
    }

    @Test
    public void testDeleteOwner() throws Exception { // <-- AGREGADO THROWS EXCEPTION AQUÍ TAMBIÉN
        Owner owner = new Owner();
        owner.setFirstName("Harold");
        owner.setLastName("Davis");
        owner.setAddress("563 Friendly St.");
        owner.setCity("Windsor");
        owner.setTelephone("6085553198");
        Owner savedOwner = ownerService.save(owner);
        Integer id = savedOwner.getId();

        ownerService.deleteById(id); 

        Assertions.assertThrows(Exception.class, () -> {
            ownerService.findById(id);
        });
        log.info("Owner con ID {} eliminado correctamente", id);
    }
}
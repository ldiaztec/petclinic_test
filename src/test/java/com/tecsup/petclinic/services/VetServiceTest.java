package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testFindVetById() {
        Integer ID = 1;
        String FIRST_NAME_EXPECTED = "James";

        Vet vet = null;
        try {
            vet = this.vetService.findById(ID);
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }
        log.info("" + vet);
        assertEquals(FIRST_NAME_EXPECTED, vet.getFirstName());
    }

    @Test
    public void testFindVetByLastName() {
        String LAST_NAME = "Carter";
        int SIZE_EXPECTED = 1;

        var vets = this.vetService.findByLastName(LAST_NAME);
        log.info("" + vets);
        assertEquals(SIZE_EXPECTED, vets.size());
    }

    @Test
    public void testCreateVet() {
        String FIRST_NAME = "Luis";
        String LAST_NAME  = "Torres";
        String EMAIL      = "luis@test.com";
        String PHONE      = "999888777";

        Vet vet = new Vet(null, FIRST_NAME, LAST_NAME, EMAIL, PHONE, true);
        Vet vetCreated = this.vetService.create(vet);

        log.info("VET CREATED: " + vetCreated);
        assertNotNull(vetCreated.getId());
        assertEquals(FIRST_NAME, vetCreated.getFirstName());
        assertEquals(LAST_NAME,  vetCreated.getLastName());
    }

    @Test
    public void testUpdateVet() {
        String FIRST_NAME    = "Ana";
        String LAST_NAME     = "Ruiz";
        String UP_FIRST_NAME = "Ana Maria";
        String UP_LAST_NAME  = "Ruiz Perez";

        Vet vet = new Vet(null, FIRST_NAME, LAST_NAME, "ana@test.com", "111222333", true);
        Vet vetCreated = this.vetService.create(vet);
        log.info("CREATED: " + vetCreated);

        vetCreated.setFirstName(UP_FIRST_NAME);
        vetCreated.setLastName(UP_LAST_NAME);
        Vet vetUpdated = this.vetService.update(vetCreated);
        log.info("UPDATED: " + vetUpdated);

        assertEquals(UP_FIRST_NAME, vetUpdated.getFirstName());
        assertEquals(UP_LAST_NAME,  vetUpdated.getLastName());
    }

    @Test
    public void testDeleteVet() {
        Vet vet = new Vet(null, "Pedro", "Lopez", "pedro@test.com", "444555666", true);
        Vet vetCreated = this.vetService.create(vet);
        log.info("CREATED: " + vetCreated);

        try {
            this.vetService.delete(vetCreated.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.vetService.findById(vetCreated.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }
}
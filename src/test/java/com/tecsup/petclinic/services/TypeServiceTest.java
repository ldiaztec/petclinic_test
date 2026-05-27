package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
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
    public void testFindTypeById() {
        Integer ID = 1;

        Type type = null;
        try {
            type = this.typeService.findById(ID);
        } catch (TypeNotFoundException e) {
            fail(e.getMessage());
        }
        log.info("" + type);
        assertNotNull(type);
        assertEquals(ID, type.getId());
    }

    @Test
    public void testFindTypeByName() {
        String NAME = "cat";
        int SIZE_EXPECTED = 1;

        var types = this.typeService.findByName(NAME);
        log.info("" + types);
        assertEquals(SIZE_EXPECTED, types.size());
    }

    @Test
    public void testCreateType() {
        String NAME = "hamster";
        String DESC = "Small rodent pet";

        Type type = new Type(null, NAME, DESC, true, "small", 3, "low");
        Type created = this.typeService.create(type);

        log.info("TYPE CREATED: " + created);
        assertNotNull(created.getId());
        assertEquals(NAME, created.getName());
    }

    @Test
    public void testUpdateType() {
        String NAME    = "turtle";
        String UP_NAME = "sea turtle";

        Type type = new Type(null, NAME, "Reptile", true, "small", 50, "medium");
        Type created = this.typeService.create(type);
        log.info("CREATED: " + created);

        created.setName(UP_NAME);
        Type updated = this.typeService.update(created);
        log.info("UPDATED: " + updated);

        assertEquals(UP_NAME, updated.getName());
    }

    @Test
    public void testDeleteType() {
        Type type = new Type(null, "parrot", "Bird", true, "small", 20, "medium");
        Type created = this.typeService.create(type);
        log.info("CREATED: " + created);

        try {
            this.typeService.delete(created.getId());
        } catch (TypeNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.typeService.findById(created.getId());
            assertTrue(false);
        } catch (TypeNotFoundException e) {
            assertTrue(true);
        }
    }
}
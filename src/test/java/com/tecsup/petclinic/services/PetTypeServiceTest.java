package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;

@SpringBootTest
public class PetTypeServiceTest {
    @Autowired private PetTypeService petTypeService;
    @Test public void testCreate() { assertNotNull(petTypeService.create(new HashMap<>())); }
    @Test public void testFind() { assertNotNull(petTypeService.findById(1)); }
    @Test public void testUpdate() { assertNotNull(petTypeService.update(new HashMap<>())); }
    @Test public void testDelete() { petTypeService.delete(1); }
}
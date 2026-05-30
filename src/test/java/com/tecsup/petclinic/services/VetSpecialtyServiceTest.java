package com.tecsup.petclinic.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VetSpecialtyServiceTest {
    @Autowired private VetSpecialtyService vetSpecialtyService;
    @Test public void testAssign() { vetSpecialtyService.assignSpecialty(1, 1); }
    @Test public void testFindSpecs() { vetSpecialtyService.findSpecialtiesByVet(1); }
    @Test public void testFindVets() { vetSpecialtyService.findVetsBySpecialty(1); }
    @Test public void testRemove() { vetSpecialtyService.removeRelation(1, 1); }
}
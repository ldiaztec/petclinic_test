package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Visit;

@SpringBootTest
public class VisitServiceTest {
    @Autowired private VisitService visitService;
    @Test public void testCreate() { assertNotNull(visitService.create(new Visit())); }
    @Test public void testFind() { assertNotNull(visitService.findById(1)); }
    @Test public void testUpdate() { assertNotNull(visitService.update(new Visit())); }
    @Test public void testDelete() { visitService.delete(1); }
}
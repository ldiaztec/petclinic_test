package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    @Test
    public void testCreateType() {
        Type type = Type.builder()
                .name("hamster")
                .build();

        Type createdType = typeService.create(type);

        assertNotNull(createdType);
        assertNotNull(createdType.getId());
        assertEquals("hamster", createdType.getName());

        log.info("Tipo de mascota creado: {}", createdType);
    }

    @Test
    public void testFindTypeById() {
        Type type = typeService.findById(1);

        assertNotNull(type);
        assertNotNull(type.getName());

        log.info("Tipo de mascota encontrado: {}", type);
    }

    @Test
    public void testFindAllTypes() {
        List<Type> types = typeService.findAll();

        assertNotNull(types);
        assertFalse(types.isEmpty());

        log.info("Cantidad de tipos encontrados: {}", types.size());
    }

    @Test
    public void testUpdateType() {
        Type type = typeService.findById(1);

        assertNotNull(type);

        type.setName("updated-type");

        Type updatedType = typeService.update(type);

        assertEquals("updated-type", updatedType.getName());

        log.info("Tipo de mascota actualizado: {}", updatedType);
    }

    @Test
    public void testDeleteType() {
        Type type = Type.builder()
                .name("temporary-type")
                .build();

        Type createdType = typeService.create(type);
        Integer id = createdType.getId();

        typeService.delete(id);

        Type deletedType = typeService.findById(id);

        assertNull(deletedType);

        log.info("Tipo de mascota eliminado con ID: {}", id);
    }

    @Test
    public void testFindTypeByName() {
        List<Type> types = typeService.findByName("cat");

        assertNotNull(types);

        log.info("Tipos encontrados por nombre: {}", types);
    }
}
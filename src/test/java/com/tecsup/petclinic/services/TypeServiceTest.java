package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;
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
    public void testCreateType() {

        TypeDTO typeDTO = TypeDTO.builder()
                .name("Perro")
                .build();

        TypeDTO createdType = typeService.create(typeDTO);

        assertNotNull(createdType);
        assertNotNull(createdType.getId());

        log.info("Type creado: " + createdType);
    }

    @Test
    public void testFindTypeById() {

        TypeDTO type = typeService.findById(1);

        assertNotNull(type);

        log.info("Type encontrado: " + type);
    }

    @Test
    public void testUpdateType() {

        TypeDTO type = typeService.findById(1);

        type.setName("Gato");

        TypeDTO updatedType = typeService.update(type);

        assertEquals("Gato", updatedType.getName());

        log.info("Type actualizado: " + updatedType);
    }

    @Test
    public void testFindAllTypes() {

        var types = typeService.findAll();

        assertNotNull(types);

        log.info("Total types: " + types.size());
    }

    @Test
    public void testDeleteType() {

        TypeDTO typeDTO = TypeDTO.builder()
                .name("Temporal")
                .build();

        TypeDTO createdType = typeService.create(typeDTO);

        Integer id = createdType.getId();

        typeService.delete(id);

        TypeDTO deletedType = typeService.findById(id);

        assertNull(deletedType);

        log.info("Type eliminado correctamente");
    }
}
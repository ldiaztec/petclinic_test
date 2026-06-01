package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
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

    /**
     * CREATE
     */
    @Test
    public void testCreateType() {

        Type type = new Type();
        type.setName("Ave");

        Type savedType = typeService.save(type);

        log.info("Tipo creado: {}", savedType);

        assertNotNull(savedType.getId());
        assertEquals("Ave", savedType.getName());
    }

    /**
     * READ
     */
    @Test
    public void testFindTypeById() {

        try {

            Type type = typeService.findById(1);

            log.info("Tipo encontrado: {}", type);

            assertNotNull(type);

        } catch (Exception e) {

            fail(e.getMessage());
        }
    }

    /**
     * UPDATE
     */
    @Test
    public void testUpdateType() {

        Type type = new Type();
        type.setName("Ave");

        Type savedType = typeService.save(type);

        savedType.setName("Ave Actualizada");

        Type updatedType = typeService.save(savedType);

        log.info("Tipo actualizado: {}", updatedType);

        assertEquals("Ave Actualizada", updatedType.getName());
    }

    /**
     * DELETE
     */
    @Test
    public void testDeleteType() {

        Type type = new Type();
        type.setName("Temporal");

        Type savedType = typeService.save(type);

        Integer id = savedType.getId();

        try {

            typeService.deleteById(id);

        } catch (Exception e) {

            fail(e.getMessage());
        }

        assertThrows(Exception.class, () -> {
            typeService.findById(id);
        });
    }
}
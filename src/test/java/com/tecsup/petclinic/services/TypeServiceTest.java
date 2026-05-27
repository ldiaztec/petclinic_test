package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;
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

        String NAME = "Hamster";

        TypeDTO typeDTO = TypeDTO.builder()
                .name(NAME)
                .build();

        TypeDTO newTypeDTO = this.typeService.create(typeDTO);

        log.info("TIPO DE MASCOTA CREADO: {}", newTypeDTO);

        assertNotNull(newTypeDTO);
        assertNotNull(newTypeDTO.getId());
        assertEquals(NAME, newTypeDTO.getName());
    }

    @Test
    public void testUpdateType() {

        String NAME = "Rabbit";
        String UPDATED_NAME = "Conejo";

        TypeDTO typeDTO = TypeDTO.builder()
                .name(NAME)
                .build();

        TypeDTO newTypeDTO = this.typeService.create(typeDTO);

        newTypeDTO.setName(UPDATED_NAME);

        TypeDTO updatedTypeDTO = this.typeService.update(newTypeDTO);

        log.info("TIPO DE MASCOTA ACTUALIZADO: {}", updatedTypeDTO);

        assertNotNull(updatedTypeDTO);
        assertEquals(newTypeDTO.getId(), updatedTypeDTO.getId());
        assertEquals(UPDATED_NAME, updatedTypeDTO.getName());
    }

    @Test
    public void testFindTypeById() {

        String NAME = "Turtle";

        TypeDTO typeDTO = TypeDTO.builder()
                .name(NAME)
                .build();

        TypeDTO newTypeDTO = this.typeService.create(typeDTO);

        TypeDTO typeFound = null;

        try {
            typeFound = this.typeService.findById(newTypeDTO.getId());
        } catch (TypeNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("TIPO DE MASCOTA ENCONTRADO POR ID: {}", typeFound);

        assertNotNull(typeFound);
        assertEquals(newTypeDTO.getId(), typeFound.getId());
        assertEquals(NAME, typeFound.getName());
    }

    @Test
    public void testFindTypeByName() {

        String NAME = "Bird";

        TypeDTO typeDTO = TypeDTO.builder()
                .name(NAME)
                .build();

        this.typeService.create(typeDTO);

        List<TypeDTO> types = this.typeService.findByName(NAME);

        log.info("TIPOS DE MASCOTA ENCONTRADOS POR NOMBRE: {}", types);

        assertNotNull(types);
        assertTrue(types.size() > 0);
    }

    @Test
    public void testDeleteType() {

        String NAME = "Fish";

        TypeDTO typeDTO = TypeDTO.builder()
                .name(NAME)
                .build();

        TypeDTO newTypeDTO = this.typeService.create(typeDTO);

        log.info("TIPO DE MASCOTA A ELIMINAR: {}", newTypeDTO);

        try {
            this.typeService.delete(newTypeDTO.getId());
        } catch (TypeNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(TypeNotFoundException.class, () -> {
            this.typeService.findById(newTypeDTO.getId());
        });
    }
}
package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testCreateOwner() {

        String FIRST_NAME = "Juan";
        String LAST_NAME = "Perez";
        String ADDRESS = "Av. Lima 123";
        String CITY = "Lima";
        String TELEPHONE = "987654321";

        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .address(ADDRESS)
                .city(CITY)
                .telephone(TELEPHONE)
                .build();

        OwnerDTO newOwnerDTO = this.ownerService.create(ownerDTO);

        log.info("DUEÑO CREADO: {}", newOwnerDTO);

        assertNotNull(newOwnerDTO);
        assertNotNull(newOwnerDTO.getId());
        assertEquals(FIRST_NAME, newOwnerDTO.getFirstName());
        assertEquals(LAST_NAME, newOwnerDTO.getLastName());
        assertEquals(ADDRESS, newOwnerDTO.getAddress());
        assertEquals(CITY, newOwnerDTO.getCity());
        assertEquals(TELEPHONE, newOwnerDTO.getTelephone());
    }

    @Test
    public void testUpdateOwner() {

        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Ramirez";
        String ADDRESS = "Jr. Los Pinos 456";
        String CITY = "Arequipa";
        String TELEPHONE = "912345678";

        String UPDATED_FIRST_NAME = "Carlos Alberto";
        String UPDATED_LAST_NAME = "Ramirez Diaz";
        String UPDATED_ADDRESS = "Av. Principal 789";
        String UPDATED_CITY = "Cusco";
        String UPDATED_TELEPHONE = "923456789";

        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .address(ADDRESS)
                .city(CITY)
                .telephone(TELEPHONE)
                .build();

        OwnerDTO newOwnerDTO = this.ownerService.create(ownerDTO);

        newOwnerDTO.setFirstName(UPDATED_FIRST_NAME);
        newOwnerDTO.setLastName(UPDATED_LAST_NAME);
        newOwnerDTO.setAddress(UPDATED_ADDRESS);
        newOwnerDTO.setCity(UPDATED_CITY);
        newOwnerDTO.setTelephone(UPDATED_TELEPHONE);

        OwnerDTO updatedOwnerDTO = this.ownerService.update(newOwnerDTO);

        log.info("DUEÑO ACTUALIZADO: {}", updatedOwnerDTO);

        assertNotNull(updatedOwnerDTO);
        assertEquals(newOwnerDTO.getId(), updatedOwnerDTO.getId());
        assertEquals(UPDATED_FIRST_NAME, updatedOwnerDTO.getFirstName());
        assertEquals(UPDATED_LAST_NAME, updatedOwnerDTO.getLastName());
        assertEquals(UPDATED_ADDRESS, updatedOwnerDTO.getAddress());
        assertEquals(UPDATED_CITY, updatedOwnerDTO.getCity());
        assertEquals(UPDATED_TELEPHONE, updatedOwnerDTO.getTelephone());
    }

    @Test
    public void testFindOwnerById() {

        String FIRST_NAME = "Luis";
        String LAST_NAME = "Torres";
        String ADDRESS = "Calle Norte 321";
        String CITY = "Trujillo";
        String TELEPHONE = "934567890";

        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .address(ADDRESS)
                .city(CITY)
                .telephone(TELEPHONE)
                .build();

        OwnerDTO newOwnerDTO = this.ownerService.create(ownerDTO);

        OwnerDTO ownerFound = null;

        try {
            ownerFound = this.ownerService.findById(newOwnerDTO.getId());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("DUEÑO ENCONTRADO POR ID: {}", ownerFound);

        assertNotNull(ownerFound);
        assertEquals(newOwnerDTO.getId(), ownerFound.getId());
        assertEquals(FIRST_NAME, ownerFound.getFirstName());
        assertEquals(LAST_NAME, ownerFound.getLastName());
    }

    @Test
    public void testFindOwnerByFirstName() {

        String FIRST_NAME = "Maria";
        String LAST_NAME = "Lopez";
        String ADDRESS = "Av. Central 654";
        String CITY = "Lima";
        String TELEPHONE = "945678901";

        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .address(ADDRESS)
                .city(CITY)
                .telephone(TELEPHONE)
                .build();

        this.ownerService.create(ownerDTO);

        List<OwnerDTO> owners = this.ownerService.findByFirstName(FIRST_NAME);

        log.info("DUEÑOS ENCONTRADOS POR NOMBRE: {}", owners);

        assertNotNull(owners);
        assertTrue(owners.size() > 0);
    }

    @Test
    public void testDeleteOwner() {

        String FIRST_NAME = "Pedro";
        String LAST_NAME = "Castillo";
        String ADDRESS = "Jr. Sur 987";
        String CITY = "Piura";
        String TELEPHONE = "956789012";

        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .address(ADDRESS)
                .city(CITY)
                .telephone(TELEPHONE)
                .build();

        OwnerDTO newOwnerDTO = this.ownerService.create(ownerDTO);

        log.info("DUEÑO A ELIMINAR: {}", newOwnerDTO);

        try {
            this.ownerService.delete(newOwnerDTO.getId());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(OwnerNotFoundException.class, () -> {
            this.ownerService.findById(newOwnerDTO.getId());
        });
    }
}
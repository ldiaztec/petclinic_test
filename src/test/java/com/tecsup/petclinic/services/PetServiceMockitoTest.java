package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exceptions.PetNotFoundException;
import com.tecsup.petclinic.repositories.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
public class PetServiceMockitoTest {

    @MockBean
    private PetRepository repository;

    @Autowired
    private PetService petService;

    @Test
    public void testFindPetById() {
        Pet petExpected = new Pet(1, "Leo", 1, 1, null);

        when(this.repository.findById(petExpected.getId()))
                .thenReturn(Optional.of(petExpected));

        PetDTO petDTO = null;
        try {
            petDTO = this.petService.findById(petExpected.getId());
        } catch (PetNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("" + petDTO);
        assertNotNull(petDTO);
        assertEquals(petExpected.getName(), petDTO.getName());
    }
}
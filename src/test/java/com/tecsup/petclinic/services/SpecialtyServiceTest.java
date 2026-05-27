package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialtyServiceImpl specialtyService;

    private Specialty specialtyTest;

    @BeforeEach
    void setUp() {
        specialtyTest = new Specialty();
        specialtyTest.setId(1);
        specialtyTest.setName("Radiología"); 
    }

    @Test
    public void testCreateSpecialty() {
        when(specialtyRepository.save(any(Specialty.class))).thenReturn(specialtyTest);

        Specialty createdSpecialty = specialtyService.create(specialtyTest);

        assertNotNull(createdSpecialty);
        assertEquals("Radiología", createdSpecialty.getName());
        verify(specialtyRepository, times(1)).save(specialtyTest);
    }

    @Test
    public void testFindSpecialtyById() {
        when(specialtyRepository.findById(1)).thenReturn(Optional.of(specialtyTest));

        Specialty foundSpecialty = specialtyService.findById(1);

        assertNotNull(foundSpecialty);
        assertEquals("Radiología", foundSpecialty.getName());
        verify(specialtyRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateSpecialty() {
        when(specialtyRepository.save(any(Specialty.class))).thenReturn(specialtyTest);

        specialtyTest.setName("Cirugía");

        Specialty updatedSpecialty = specialtyService.update(specialtyTest);

        assertNotNull(updatedSpecialty);
        assertEquals("Cirugía", updatedSpecialty.getName());
        verify(specialtyRepository, times(1)).save(specialtyTest);
    }

    @Test
    public void testDeleteSpecialty() {
        doNothing().when(specialtyRepository).deleteById(1);

        
        specialtyService.delete(1);

        verify(specialtyRepository, times(1)).deleteById(1);
    }
}
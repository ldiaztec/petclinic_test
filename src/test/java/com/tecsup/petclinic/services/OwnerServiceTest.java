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

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    // Inyectamos los mocks en la implementación concreta del servicio
    @InjectMocks
    private OwnerServiceImpl ownerService;

    private Owner ownerTest;

    @BeforeEach
    void setUp() {
        ownerTest = new Owner();
        // Nota: Asegúrate de que tu entidad Owner tenga estos métodos setter
        ownerTest.setId(1);
        ownerTest.setFirstName("Juan");
        ownerTest.setLastName("Perez");
        ownerTest.setAddress("Av. Los Incas 123");
        ownerTest.setCity("Arequipa");
        ownerTest.setTelephone("987654321");
    }

    @Test
    public void testCreateOwner() {
        // Preparar
        when(ownerRepository.save(any(Owner.class))).thenReturn(ownerTest);

        // Ejecutar (Asumiendo que el método en OwnerService se llama create o save)
        Owner createdOwner = ownerService.create(ownerTest); 

        // Validar
        assertNotNull(createdOwner);
        assertEquals("Juan", createdOwner.getFirstName());
        verify(ownerRepository, times(1)).save(ownerTest);
    }

    @Test
    public void testFindOwnerById() {
        // Preparar
        when(ownerRepository.findById(1)).thenReturn(Optional.of(ownerTest));

        // Ejecutar
        Owner foundOwner = ownerService.findById(1);

        // Validar
        assertNotNull(foundOwner);
        assertEquals("Perez", foundOwner.getLastName());
        verify(ownerRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateOwner() {
        // Preparar
        when(ownerRepository.save(any(Owner.class))).thenReturn(ownerTest);

        // Modificar dato
        ownerTest.setCity("Lima");

        // Ejecutar
        Owner updatedOwner = ownerService.update(ownerTest);

        // Validar
        assertNotNull(updatedOwner);
        assertEquals("Lima", updatedOwner.getCity());
        verify(ownerRepository, times(1)).save(ownerTest);
    }

    @Test
    public void testDeleteOwner() {
        // Preparar
        doNothing().when(ownerRepository).deleteById(1);

        // Ejecutar
        ownerService.delete(1);

        // Validar
        verify(ownerRepository, times(1)).deleteById(1);
    }
}
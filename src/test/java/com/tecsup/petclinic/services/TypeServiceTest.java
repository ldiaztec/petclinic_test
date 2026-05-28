package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.repositories.TypeRepository;

import org.junit.jupiter.api.Test; // Permite crear pruebas unitarias
import org.springframework.beans.factory.annotation.Autowired; // Inyecta objetos automáticamente
import org.springframework.boot.test.context.SpringBootTest; // Levanta el contexto de Spring Boot

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Inicia la aplicación para realizar las pruebas
public class TypeServiceTest {

    @Autowired // Inyecta automáticamente el repositorio
    private TypeRepository typeRepository;

    @Test // Prueba de creación
    public void testCreateType() {

        // Crear objeto Type
        Type type = new Type();
        type.setName("Perro");

        // Guardar en la base de datos
        Type savedType = typeRepository.save(type);

        // Verifica que el ID no sea null
        assertNotNull(savedType.getId());
    }

    @Test // Prueba de búsqueda
    public void testFindType() {

        // Crear objeto Type
        Type type = new Type();
        type.setName("Gato");

        // Guardar en la base de datos
        Type savedType = typeRepository.save(type);

        // Buscar por ID
        Optional<Type> found =
                typeRepository.findById(savedType.getId());

        // Verifica que el registro exista
        assertTrue(found.isPresent());
    }

    @Test // Prueba de actualización
    public void testUpdateType() {

        // Crear objeto Type
        Type type = new Type();
        type.setName("Ave");

        // Guardar registro
        Type savedType = typeRepository.save(type);

        // Actualizar nombre
        savedType.setName("Conejo");

        // Guardar cambios
        Type updatedType = typeRepository.save(savedType);

        // Verifica que el nombre cambió correctamente
        assertEquals(
                "Conejo",
                updatedType.getName()
        );
    }

    @Test // Prueba de eliminación
    public void testDeleteType() {

        // Crear objeto Type
        Type type = new Type();
        type.setName("Hamster");

        // Guardar registro
        Type savedType = typeRepository.save(type);

        // Obtener ID
        Integer id = savedType.getId();

        // Eliminar registro
        typeRepository.deleteById(id);

        // Buscar nuevamente el registro eliminado
        Optional<Type> deleted =
                typeRepository.findById(id);

        // Verifica que el registro ya no exista
        assertFalse(deleted.isPresent());
    }
}
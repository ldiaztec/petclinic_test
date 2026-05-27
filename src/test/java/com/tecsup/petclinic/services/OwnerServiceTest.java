package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	// Prueba de creación de dueño
	@Test
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setFirstName("Carlos");
		owner.setLastName("Mendoza");
		owner.setAddress("Av. Las Flores 456");
		owner.setCity("Lima");
		owner.setTelephone("987654321");

		try {
			Owner createdOwner = ownerService.create(owner);
			log.info("Dueño registrado con éxito: " + createdOwner);

			assertNotNull(createdOwner.getId(), "El ID no debería ser nulo");
			assertEquals("Carlos", createdOwner.getFirstName());
		} catch (Exception e) {
			fail("Error al crear el dueño: " + e.getMessage());
		}
	}

	// Prueba de búsqueda por ID
	@Test
	public void testFindOwnerById() {
		Long ID_A_BUSCAR = 1L;

		try {
			Owner owner = ownerService.findById(ID_A_BUSCAR);
			log.info("Dueño encontrado: " + owner);

			assertNotNull(owner, "El dueño no debería ser nulo");
			assertEquals(ID_A_BUSCAR, owner.getId());
		} catch (Exception e) {
			fail("Error al buscar el dueño: " + e.getMessage());
		}
	}

	// Prueba de actualización
	@Test
	public void testUpdateOwner() {
		Long ID_A_MODIFICAR = 1L;
		String NUEVO_NOMBRE = "George Modificado";

		try {
			Owner ownerExistente = ownerService.findById(ID_A_MODIFICAR);
			assertNotNull(ownerExistente, "El dueño a modificar debe existir");

			ownerExistente.setFirstName(NUEVO_NOMBRE);
			Owner ownerActualizado = ownerService.update(ownerExistente);
			log.info("Dueño actualizado con éxito: " + ownerActualizado);

			assertEquals(NUEVO_NOMBRE, ownerActualizado.getFirstName());
		} catch (Exception e) {
			fail("Error al actualizar el dueño: " + e.getMessage());
		}
	}

	// Prueba de eliminación
	@Test
	public void testDeleteOwner() {
		Owner ownerParaBorrar = new Owner();
		ownerParaBorrar.setFirstName("Dueño");
		ownerParaBorrar.setLastName("Temporal");
		ownerParaBorrar.setAddress("Calle Falsa 123");
		ownerParaBorrar.setCity("Arequipa");
		ownerParaBorrar.setTelephone("951753456");

		try {
			Owner creado = ownerService.create(ownerParaBorrar);
			Long idCreado = creado.getId();
			log.info("Dueño temporal creado con ID: " + idCreado);

			ownerService.delete(idCreado);
			log.info("Dueño con ID " + idCreado + " eliminado correctamente.");

			try {
				Owner buscado = ownerService.findById(idCreado);
				assertNull(buscado, "El dueño debería ser nulo tras eliminarlo");
			} catch (Exception e) {
				log.info("Confirmado: No se pudo encontrar el dueño porque fue eliminado con éxito.");
			}

		} catch (Exception e) {
			fail("Error en el proceso de eliminación: " + e.getMessage());
		}
	}
}

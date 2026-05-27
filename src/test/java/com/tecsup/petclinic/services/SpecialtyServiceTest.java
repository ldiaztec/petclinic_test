package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
@Slf4j
public class SpecialtyServiceTest {

	@Autowired
	private SpecialtyService specialtyService;

	// 1. Prueba de Creación
	@Test
	public void testCreateSpecialty() {
		Specialty specialty = new Specialty();
		specialty.setName("Odontología");

		try {
			Specialty created = specialtyService.create(specialty);
			log.info("Especialidad registrada con éxito: " + created);

			assertNotNull(created.getId(), "El ID no debería ser nulo");
			assertEquals("Odontología", created.getName());
		} catch (Exception e) {
			fail("Error al crear la especialidad: " + e.getMessage());
		}
	}

	// 2. Prueba de Búsqueda por ID
	@Test
	public void testFindSpecialtyById() {
		Long ID_A_BUSCAR = 1L;

		try {
			Specialty specialty = specialtyService.findById(ID_A_BUSCAR);
			log.info("Especialidad encontrada: " + specialty);

			assertNotNull(specialty, "La especialidad no debería ser nula");
			assertEquals(ID_A_BUSCAR, specialty.getId());
		} catch (Exception e) {
			fail("Error al buscar la especialidad: " + e.getMessage());
		}
	}

	// 3. Prueba de Actualización
	@Test
	public void testUpdateSpecialty() {
		Long ID_A_MODIFICAR = 1L;
		String NUEVO_NOMBRE = "Radiología Avanzada";

		try {
			Specialty existente = specialtyService.findById(ID_A_MODIFICAR);
			assertNotNull(existente, "La especialidad a modificar debe existir");

			existente.setName(NUEVO_NOMBRE);
			Specialty actualizado = specialtyService.update(existente);
			log.info("Especialidad actualizada con éxito: " + actualizado);

			assertEquals(NUEVO_NOMBRE, actualizado.getName());
		} catch (Exception e) {
			fail("Error al actualizar la especialidad: " + e.getMessage());
		}
	}

	// 4. Prueba de Eliminación
	@Test
	public void testDeleteSpecialty() {
		Specialty temporal = new Specialty();
		temporal.setName("Especialidad Temporal");

		try {
			Specialty creado = specialtyService.create(temporal);
			Long idCreado = creado.getId();
			log.info("Especialidad temporal creada con ID: " + idCreado);

			specialtyService.delete(idCreado);
			log.info("Especialidad con ID " + idCreado + " eliminada correctamente.");

			try {
				Specialty buscado = specialtyService.findById(idCreado);
				assertNull(buscado, "La especialidad debería ser nula tras eliminarla");
			} catch (Exception e) {
				log.info("Confirmado: No se pudo encontrar la especialidad porque fue eliminada con éxito.");
			}

		} catch (Exception e) {
			fail("Error en el proceso de eliminación: " + e.getMessage());
		}
	}
}

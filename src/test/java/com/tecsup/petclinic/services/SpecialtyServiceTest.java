package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

	@Autowired
	private SpecialtyService specialtyService;

	@Test
	public void testFindSpecialtyById() {
		Integer ID = 1;
		String NAME_EXPECTED = "radiology";

		Specialty specialty = null;
		try {
			specialty = this.specialtyService.findById(ID);
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("" + specialty);
		assertEquals(NAME_EXPECTED, specialty.getName());
	}

	@Test
	public void testFindSpecialtyByName() {
		String NAME = "surgery";
		int SIZE_EXPECTED = 1;

		var specialties = this.specialtyService.findByName(NAME);
		log.info("" + specialties);
		assertEquals(SIZE_EXPECTED, specialties.size());
	}

	@Test
	public void testCreateSpecialty() {
		String NAME     = "neurology";
		String OFFICE   = "Room 5";
		Integer H_OPEN  = 8;
		Integer H_CLOSE = 17;

		Specialty specialty = new Specialty(null, NAME, OFFICE, H_OPEN, H_CLOSE);
		Specialty created = this.specialtyService.create(specialty);

		log.info("SPECIALTY CREATED: " + created);
		assertNotNull(created.getId());
		assertEquals(NAME, created.getName());
	}

	@Test
	public void testUpdateSpecialty() {
		String NAME    = "cardiology";
		String UP_NAME = "cardiology advanced";

		Specialty specialty = new Specialty(null, NAME, "Room 6", 9, 18);
		Specialty created = this.specialtyService.create(specialty);
		log.info("CREATED: " + created);

		created.setName(UP_NAME);
		Specialty updated = this.specialtyService.update(created);
		log.info("UPDATED: " + updated);

		assertEquals(UP_NAME, updated.getName());
	}

	@Test
	public void testDeleteSpecialty() {
		Specialty specialty = new Specialty(null, "oncology", "Room 7", 10, 16);
		Specialty created = this.specialtyService.create(specialty);
		log.info("CREATED: " + created);

		try {
			this.specialtyService.delete(created.getId());
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			this.specialtyService.findById(created.getId());
			assertTrue(false);
		} catch (SpecialtyNotFoundException e) {
			assertTrue(true);
		}
	}
}
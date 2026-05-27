package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testFindOwnerById() {
		Integer ID = 1;
		String FIRST_NAME_EXPECTED = "George";

		Owner owner = null;
		try {
			owner = this.ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("" + owner);
		assertEquals(FIRST_NAME_EXPECTED, owner.getFirstName());
	}

	@Test
	public void testFindOwnerByLastName() {
		String LAST_NAME = "Franklin";
		int SIZE_EXPECTED = 1;

		var owners = this.ownerService.findByLastName(LAST_NAME);
		log.info("" + owners);
		assertEquals(SIZE_EXPECTED, owners.size());
	}

	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "Carlos";
		String LAST_NAME  = "Mendez";
		String ADDRESS    = "Av. Lima 123";
		String CITY       = "Trujillo";
		String TELEPHONE  = "044123456";

		Owner owner = new Owner(null, FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner ownerCreated = this.ownerService.create(owner);

		log.info("OWNER CREATED: " + ownerCreated);
		assertNotNull(ownerCreated.getId());
		assertEquals(FIRST_NAME, ownerCreated.getFirstName());
		assertEquals(LAST_NAME,  ownerCreated.getLastName());
	}

	@Test
	public void testUpdateOwner() {
		String FIRST_NAME    = "Maria";
		String LAST_NAME     = "Gomez";
		String UP_FIRST_NAME = "Maria Elena";
		String UP_LAST_NAME  = "Gomez Rios";

		Owner owner = new Owner(null, FIRST_NAME, LAST_NAME, "Calle 1", "Lima", "011111111");
		Owner ownerCreated = this.ownerService.create(owner);
		log.info("CREATED: " + ownerCreated);

		ownerCreated.setFirstName(UP_FIRST_NAME);
		ownerCreated.setLastName(UP_LAST_NAME);
		Owner ownerUpdated = this.ownerService.update(ownerCreated);
		log.info("UPDATED: " + ownerUpdated);

		assertEquals(UP_FIRST_NAME, ownerUpdated.getFirstName());
		assertEquals(UP_LAST_NAME,  ownerUpdated.getLastName());
	}

	@Test
	public void testDeleteOwner() {
		Owner owner = new Owner(null, "Rosa", "Vargas", "Jr. Paz 99", "Chiclayo", "074999999");
		Owner ownerCreated = this.ownerService.create(owner);
		log.info("CREATED: " + ownerCreated);

		try {
			this.ownerService.delete(ownerCreated.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			this.ownerService.findById(ownerCreated.getId());
			assertTrue(false);
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		}
	}
}
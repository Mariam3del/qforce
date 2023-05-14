package nl.qnh.qforce.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import nl.qnh.qforce.domain.StarWarsPerson;
import nl.qnh.qforce.service.PersonServiceImpl;

class StarWarsControllerTest {

	@Test
	void testgetStarWarsPersonById() {
		StarWarsController starWarsController = new StarWarsController();
		PersonServiceImpl mock = mock(PersonServiceImpl.class);
		StarWarsPerson person = new StarWarsPerson();
		person.setName("Mariam");
		person.setBirthYear("1982");
		when(mock.get(1)).thenReturn(Optional.of(person));
		starWarsController.setService(mock);
		
		String jsonPerson = starWarsController.getStarWarsPersonById(1);
		assertNotNull(jsonPerson);
		assertTrue(jsonPerson.contains("Mariam"));
		assertTrue(jsonPerson.contains("1982"));
	}
	@Test
	void testgetStarWarsPersonByIdNoPersonFound() {
		StarWarsController starWarsController = new StarWarsController();
		PersonServiceImpl mock = mock(PersonServiceImpl.class);
		when(mock.get(1)).thenReturn(Optional.empty());
		starWarsController.setService(mock);
		
		assertThrows(ResponseStatusException.class, () -> starWarsController.getStarWarsPersonById(1));
	}

}

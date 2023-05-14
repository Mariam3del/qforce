package nl.qnh.qforce.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import nl.qnh.qforce.domain.StarWarsPerson;
import nl.qnh.qforce.service.StarWarsService;

class StarWarsControllerTest {

	@Test
	void testgetStarWarsPersonById() {
		StarWarsController starWarsController = new StarWarsController();
		StarWarsService mock = mock(StarWarsService.class);
		StarWarsPerson person = new StarWarsPerson();
		person.setName("Mariam");
		person.setBirthYear("1982");
		when(mock.getStarWarsPerson(1)).thenReturn(Optional.of(person));
		starWarsController.setService(mock);
		
		StarWarsPerson result = starWarsController.getStarWarsPersonById(1);
		assertNotNull(result);
		assertEquals(person.getName(), result.getName());
		assertEquals(person.getBirthYear(), result.getBirthYear());
	}
	@Test
	void testgetStarWarsPersonByIdNoPersonFound() {
		StarWarsController starWarsController = new StarWarsController();
		StarWarsService mock = mock(StarWarsService.class);
		when(mock.getStarWarsPerson(1)).thenReturn(Optional.empty());
		starWarsController.setService(mock);
		
		assertThrows(ResponseStatusException.class, () -> starWarsController.getStarWarsPersonById(1));
	}

}

package nl.qnh.qforce.swapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import nl.qnh.qforce.domain.Person;

class StarWarsPersonServicTest {

	@Test
	void testGettingOnePerson() {
		SWService service = new SWService();
		Optional<Person> person = service.getStarWarsPerson(1);
		assertNotNull(person);
	}

}

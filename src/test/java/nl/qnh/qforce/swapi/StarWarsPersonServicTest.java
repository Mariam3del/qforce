package nl.qnh.qforce.swapi;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import nl.qnh.qforce.domain.StarWarsPerson;

/**
 * Test class that actually calls the backend
 * @author mino
 *
 */
class StarWarsPersonServicTest {
	SWService service = new SWService();
	@Test
	void testGettingOnePerson() {
		Optional<StarWarsPerson> person = service.getStarWarsPerson(1);
		assertNotNull(person);
	}
	@Test
	void testGettingAnotherPerson() {
		Optional<StarWarsPerson> person = service.getStarWarsPerson(3);
		assertNotNull(person);
	}
	@Test
	void testGettingFemalePerson() {
		Optional<StarWarsPerson> person = service.getStarWarsPerson(5);
		assertNotNull(person);
	}
	//test person with gender is none
	@Test
	void testGettingGenderNonePerson() {
		Optional<StarWarsPerson> person = service.getStarWarsPerson(23);
		assertNotNull(person);
	}
	@Test
	void testGettingSomePersons() {
		List<StarWarsPerson> starWarsPersons = service.getStarWarsPersons("Luke");
		assertNotNull(starWarsPersons);
		assertNotEquals(0, starWarsPersons.size());
	}
	@Test
	void testGettingMoreThanOnePerson() {
		List<StarWarsPerson> starWarsPersons = service.getStarWarsPersons("Skywalker");
		assertNotNull(starWarsPersons);
		assertNotEquals(0, starWarsPersons.size());
		//we do know there is more than one skywalker
		assertNotEquals(1, starWarsPersons);
	}
	
}

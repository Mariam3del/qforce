package nl.qnh.qforce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import nl.qnh.qforce.domain.StarWarsPerson;
import nl.qnh.qforce.swapi.SWService;

class StarWarsServiceTest {
	StarWarsService personServiceImpl = new StarWarsService();

	@Test
	void testSearch() {
		// Create mock result
		StarWarsPerson person1 = new StarWarsPerson();
		StarWarsPerson person2 = new StarWarsPerson();
		List<StarWarsPerson> persons = List.of(person1, person2);

		// mock starwars service
		SWService starWarsPersonServiceMock = mock(SWService.class);
		when(starWarsPersonServiceMock.getStarWarsPersons("search")).thenReturn(persons);

		// mock analytics service
		AnalyticsService analyticsService = mock(AnalyticsService.class);

		// set it in the service I want to test
		personServiceImpl.setStarWarsPersonService(starWarsPersonServiceMock);
		personServiceImpl.setAnalyticsService(analyticsService);

		// finally invoke the service
		List<StarWarsPerson> searchResults = personServiceImpl.searchStarWarsPersons("search");

		// check if the results are correct
		assertNotNull(searchResults);
		assertEquals(2, searchResults.size());

	}

	@Test
	void testSearchWithEmptyString() {
		// Create mock result which is an empty list
		List<StarWarsPerson> persons = new ArrayList<StarWarsPerson>();

		// mock star wars service
		SWService starWarsPersonServiceMock = mock(SWService.class);
		when(starWarsPersonServiceMock.getStarWarsPersons("search")).thenReturn(persons);

		// mock analytics service
		AnalyticsService analyticsService = mock(AnalyticsService.class);

		// set it in the service I want to test
		personServiceImpl.setStarWarsPersonService(starWarsPersonServiceMock);
		personServiceImpl.setAnalyticsService(analyticsService);

		// finally invoke the service
		List<StarWarsPerson> searchResults = personServiceImpl.searchStarWarsPersons("search");

		// check if the results are correct
		assertNotNull(searchResults);
		assertEquals(0, searchResults.size());
	}

	@Test
	void testGet() {
		// Create mock result which is an empty list
		StarWarsPerson person = new StarWarsPerson();
		person.setName("Skywalker");

		// mock star wars service
		SWService starWarsPersonServiceMock = mock(SWService.class);
		when(starWarsPersonServiceMock.getStarWarsPerson(2)).thenReturn(Optional.of(person));

		// mock analytics service
		AnalyticsService analyticsService = mock(AnalyticsService.class);

		// set it in the service I want to test
		personServiceImpl.setStarWarsPersonService(starWarsPersonServiceMock);
		personServiceImpl.setAnalyticsService(analyticsService);

		// finally invoke the service
		Optional<StarWarsPerson> searchResults = personServiceImpl.getStarWarsPerson(2);

		// check if the results are correct
		assertNotNull(searchResults);
		assertEquals("Skywalker", person.getName());
		
	}

	@Test
	void testGetWithEmptyID() {

		// mock starwars service
		SWService starWarsPersonServiceMock = mock(SWService.class);
		when(starWarsPersonServiceMock.getStarWarsPerson(2)).thenReturn(Optional.empty());

		// mock analytics service
		AnalyticsService analyticsService = mock(AnalyticsService.class);

		// set it in the service I want to test
		personServiceImpl.setStarWarsPersonService(starWarsPersonServiceMock);
		personServiceImpl.setAnalyticsService(analyticsService);

		// finally invoke the service
		Optional<StarWarsPerson> searchResults = personServiceImpl.getStarWarsPerson(2);

		// check if the results are correct
		assertNotNull(searchResults);
		assertEquals(Optional.empty(), searchResults);
		
	}

}

package nl.qnh.qforce.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.domain.StarWarsPerson;
import nl.qnh.qforce.swapi.SWService;

/*

 */

/**
 * 
 * This is our starting point. Here comes the request and here we invoke the
 * appropriate back end services
 * 
 * The class offers two methods one for searching and one for fetching.
 * 
 * @author Mariam Adel
 *
 */
public class PersonServiceImpl implements PersonService {
	private static Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	// we only have one service. if more services exist we should use a factory to
	// return relevant service
	private SWService starWarsPersonService = new SWService();

	//this service is used to increment the statistics
	private AnalyticsService analyticsService = new AnalyticsService();
	/**
	 * Method to be used when searching for Person object. It returns List of
	 * Persons that match the criteria
	 */
	@Override
	public List<Person> search(String query) {
		LOGGER.info("Searching with query: " + query);
		//Why am I doing this? is there another way to return List of persons???
		//TODO: find a cleaner way
		List<Person> persons = List.copyOf( getStarWarsPersons(query));
		return persons;
	}

	/**
	 * Method used to return only one person by ID
	 */
	@Override
	public Optional<Person> get(long id) {
		LOGGER.debug("Searching by id: " + id);
		return getStarWarsPerson(id);

	}

	private List<StarWarsPerson> getStarWarsPersons(String query) {
		//Increment statistics
		analyticsService.incrementStarWarsStats();
		//call the appropriate service
		return  starWarsPersonService.getStarWarsPersons(query);
	}

	private Optional<Person> getStarWarsPerson(long id) {
		//Increment statistics
		analyticsService.incrementStarWarsStats();
		//call the appropriate service
		return starWarsPersonService.getStarWarsPerson(id);
	}
	
	
	public SWService getStarWarsPersonService() {
		return starWarsPersonService;
	}

	public void setStarWarsPersonService(SWService starWarsPersonService) {
		this.starWarsPersonService = starWarsPersonService;
	}

	public AnalyticsService getAnalyticsService() {
		return analyticsService;
	}

	public void setAnalyticsService(AnalyticsService analyticsService) {
		this.analyticsService = analyticsService;
	}

}

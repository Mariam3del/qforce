package nl.qnh.qforce.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.qnh.qforce.domain.StarWarsPerson;
import nl.qnh.qforce.swapi.SWService;

/*

 */

/**
 * 
 * This is our starting point. Here comes the request and here we invoke the
 * appropriate back end services
 * 
 * The class offers two methods one for searching and one for fetching. I
 * decided not to use the Personservice provided because of limitations
 * 
 * @author Mariam Adel
 *
 */
public class StarWarsService  {
	private static Logger LOGGER = LoggerFactory.getLogger(StarWarsService.class);

	// we only have one service. if more services exist we should use a factory to
	// return relevant service
	private SWService starWarsPersonService = new SWService();


	/**
	 * use this method if you want to get a list of starwars persons that fit the
	 * search query
	 * 
	 * @param query
	 * @return
	 */
	public List<StarWarsPerson> searchStarWarsPersons(String query) {
		LOGGER.info("Searching with query: " + query);
		// call the appropriate service
		return starWarsPersonService.getStarWarsPersons(query);

	}

	public Optional<StarWarsPerson> getStarWarsPerson(long id) {
		LOGGER.debug("Searching by id: " + id);
		// call the appropriate service
		return starWarsPersonService.getStarWarsPerson(id);

	}

	public SWService getStarWarsPersonService() {
		return starWarsPersonService;
	}

	public void setStarWarsPersonService(SWService starWarsPersonService) {
		this.starWarsPersonService = starWarsPersonService;
	}
}

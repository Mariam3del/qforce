package nl.qnh.qforce.swapi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.domain.StarWarsPeopleSearchResults;
import nl.qnh.qforce.domain.StarWarsPerson;

/**
 * 
 * This class is our star wars service. It is responsible for calling the SWAPI
 * and marshaling the JSON response.
 * 
 * 
 * @author Mariam Adel
 *
 */
public class SWService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SWService.class);
	
	// in a perfect world this should be in a properties file
	private static String SWAPI_URL = "https://swapi.dev/api/people/";

	

	public  Optional<Person> getStarWarsPerson(long id) {
		LOGGER.info("getStarWarsPerson"+ id);
		StarWarsPerson person = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			person = mapper.readValue(buildURL(id), StarWarsPerson.class);
			
		} catch (IOException e) {
			LOGGER.error("Error while trying to call service by id",e);
		}
		LOGGER.debug("for id : "+id+" person is returned successfully: "+person);
		return Optional.ofNullable(person);
	}
	public  List<StarWarsPerson> getStarWarsPersons(String query) {
		StarWarsPeopleSearchResults results = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			results = mapper.readValue(buildURL(query), StarWarsPeopleSearchResults.class);
		} catch (IOException e) {
			LOGGER.error("Error while trying to call service using search function",e);
		}
		LOGGER.debug("for query : "+query+" number of persons returned :"+ Optional.of(results.getResults().size()).orElse(0 ));
		
		return results.getResults();
	}
	/**
	 * build URL for retrieving by id function
	 * @param id
	 * @return
	 * @throws MalformedURLException
	 */
	private  URL buildURL(long id) throws MalformedURLException {
		return new URL(SWAPI_URL + id+"/?format=json");
	}
	/**
	 * build URL for retrieving using search function
	 * @param search
	 * @return
	 * @throws MalformedURLException
	 */
	private  URL buildURL(String search) throws MalformedURLException {
		return new URL(SWAPI_URL+"?format=json&search="+search);
	}
}

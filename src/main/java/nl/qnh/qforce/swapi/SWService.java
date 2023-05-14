package nl.qnh.qforce.swapi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.qnh.qforce.domain.StarWarsMovies;
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
	private static String SWAPI_PERSON_URL = "https://swapi.dev/api/people/";
	private ObjectMapper mapper = new ObjectMapper();

	public Optional<StarWarsPerson> getStarWarsPerson(long id) {
		LOGGER.info("getStarWarsPerson" + id);
		StarWarsPerson person = null;
		try {

			person = mapper.readValue(buildURL(id), StarWarsPerson.class);
			setMovies(person);

		} catch (IOException e) {
			LOGGER.error("Error while trying to call service by id", e);
		}
		LOGGER.debug("for id : " + id + " person is returned successfully: " + person);
		return Optional.ofNullable(person);
	}

	/**
	 * This method calls swapi API and returns a results object which will contain a
	 * list of star wars persons that matches my search
	 * 
	 * @param query the search query to use for searching
	 * @return List of starWarsPersons
	 */
	public List<StarWarsPerson> getStarWarsPersons(String query) {
		LOGGER.debug("getStarWarsPersons: " + query);
		StarWarsPeopleSearchResults results = new StarWarsPeopleSearchResults();
		try {
			results = mapper.readValue(buildURL(query), StarWarsPeopleSearchResults.class);
			if (results.getResults() != null) {
				for (StarWarsPerson starWarsPerson : results.getResults()) {
					setMovies(starWarsPerson);
				}
			}
		} catch (IOException e) {
			LOGGER.error("Error while trying to call service using search function", e);
		}
		LOGGER.debug("for query : " + query + " number of persons returned :"
				+ Optional.of(results.getResults().size()).orElse(0));

		return results.getResults();
	}

	/**
	 * this method will map every film url return in person to a list of
	 * starWarMovies
	 * 
	 * @param person
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	private void setMovies(StarWarsPerson person) throws MalformedURLException, IOException {
		LOGGER.debug("populating the movies of person: " + person.getName());
		List<StarWarsMovies> movies = new ArrayList<StarWarsMovies>();
		// map the list of movies
		for (String film : person.getFilms()) {
			// get the film and add to movies
			// https://swapi.dev/api/films/?format=json
			StarWarsMovies movie = mapper.readValue(buildFilmURL(film), StarWarsMovies.class);
			movies.add(movie);
		}
		person.setMovies(movies);
	}

	/**
	 * build URL for retrieving by id function
	 * 
	 * @param id
	 * @return
	 * @throws MalformedURLException
	 */
	private URL buildURL(long id) throws MalformedURLException {
		return new URL(SWAPI_PERSON_URL + id + "/?format=json");
	}

	/**
	 * build URL for retrieving using search function
	 * 
	 * @param search
	 * @return
	 * @throws MalformedURLException
	 */
	private URL buildURL(String search) throws MalformedURLException {
		return new URL(SWAPI_PERSON_URL + "?format=json&search=" + search);
	}

	/**
	 * build URL for retrieving using search function
	 * 
	 * @param search
	 * @return
	 * @throws MalformedURLException
	 */
	private URL buildFilmURL(String film) throws MalformedURLException {
		return new URL(film + "?format=json");
	}
}

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

	private final static Logger LOGGER = LoggerFactory.getLogger(SWService.class);

	// in a perfect world this should be in a properties file
	private final static String SWAPI_PERSON_URL = "https://swapi.dev/api/people/";
	private final ObjectMapper mapper = new ObjectMapper();

	public Optional<StarWarsPerson> getStarWarsPerson(long id) {
        LOGGER.info("getStarWarsPerson{}", id);
		StarWarsPerson person = null;
		try {

			person = mapper.readValue(buildURL(id), StarWarsPerson.class);
			setMovies(person);

		} catch (IOException e) {
			LOGGER.error("Error while trying to call service by id", e);
		}
        LOGGER.debug("for id : {} person is returned successfully: {}", id, person);
		return Optional.ofNullable(person);
	}

	/**
	 * This method calls swapi API and returns a results object which will contain a
	 * list of star wars persons that matches my search.
	 * 
	 * @param query the search query to use for searching.
	 * @return List of starWarsPersons.
	 */
	public List<StarWarsPerson> getStarWarsPersons(String query,int page) {
		LOGGER.debug("getStarWarsPersons: " + query);
		StarWarsPeopleSearchResults results = new StarWarsPeopleSearchResults();
		try {
			results = mapper.readValue(buildURL(query,page), StarWarsPeopleSearchResults.class);
			if (results.getResults() != null) {
				for (StarWarsPerson starWarsPerson : results.getResults()) {
					setMovies(starWarsPerson);
				}
			}
			//if results contains more pages. get the next page

			//commenting this out coz it makes the service very slow. consider pagination?
			
//			if(results.getNext()!=null && results.getNext()!="") {
//				List<StarWarsPerson> starWarsPersonsNextPage = getStarWarsPersons(query, page++);
//				//add the next results to the results
//				results.getResults().addAll(starWarsPersonsNextPage);
//			}
		} catch (IOException e) {
			LOGGER.error("Error while trying to call service using search function", e);
		}
        LOGGER.debug("for query : {} number of persons returned :{}", query, Optional.of(results.getResults().size()).orElse(0));

		return results.getResults();
	}

	private void setMovies(StarWarsPerson person) throws IOException {
		LOGGER.debug("populating the movies of person: " + person.getName());
		List<StarWarsMovies> movies = new ArrayList<>();
		// map the list of movies
		for (String film : person.getFilms()) {
			// get the film and add to movies
			// https://swapi.dev/api/films/?format=json
			StarWarsMovies movie = mapper.readValue(buildFilmURL(film), StarWarsMovies.class);
			movies.add(movie);
		}
		//clean up films from person. it's not needed anymore
		person.setFilms(null);
		person.setMovies(movies);
	}

	/**
	 * build URL for retrieving by id function.
	 * 
	 * @param id is of person.
	 * @return url of person service.
	 * @throws MalformedURLException in case of exception.
	 */
	private URL buildURL(long id) throws MalformedURLException {
		return new URL(SWAPI_PERSON_URL + id + "/?format=json");
	}

	/**
	 * build URL for retrieving using search function.
	 * 
	 * @param search search page.
	 * @return URL.
	 * @throws MalformedURLException in case of exception.
	 */
	private URL buildURL(String search, int page) throws MalformedURLException {
		return new URL(SWAPI_PERSON_URL + "?format=json&search=" + search+"&page="+page);
	}

	/**
	 * build URL for retrieving using search function.
	 * 
	 * @param film string to search with.
	 * @return URL for film.
	 * @throws MalformedURLException in case of malformed url.
	 */
	private URL buildFilmURL(String film) throws MalformedURLException {
		return new URL(film + "?format=json");
	}
}

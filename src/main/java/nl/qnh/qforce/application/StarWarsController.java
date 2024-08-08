package nl.qnh.qforce.application;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nl.qnh.qforce.database.model.Stats;
import nl.qnh.qforce.database.repository.StatsRepository;
import nl.qnh.qforce.domain.ServiceUsed;
import nl.qnh.qforce.domain.StarWarsPerson;
import nl.qnh.qforce.service.StarWarsService;

@RestController
public class StarWarsController {

	private final static Logger LOGGER = LoggerFactory.getLogger(StarWarsController.class);

	private final StarWarsService starWarsService ;

	private final StatsRepository repository;

	public StarWarsController(StarWarsService starWarsService, StatsRepository repository) {
		this.starWarsService = starWarsService;
		this.repository = repository;
	}


	/**
	 * Method searches for persons using query or else returns 404.
	 * 
	 * @param query query to search with.
	 * @return JSON string.
	 */
	@GetMapping("/persons/")
	List<StarWarsPerson> getStarWarsPersons(@RequestParam(name= "q") String query) {
        LOGGER.debug("Getting all persons with query: {}", query);
		InsertOrUpdateRepository(ServiceUsed.SEARCH);
		List<StarWarsPerson> searchResults = starWarsService.searchStarWarsPersons(query);
		if (searchResults.isEmpty()) {
			LOGGER.debug("No results found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Query didn't return anything");
		}
		return searchResults;
	}


	/**
	 * Method searches for persons using id or else returns 404
	 */
	@GetMapping("/persons/{id}")
	StarWarsPerson getStarWarsPersonById(@PathVariable long id) {
		Optional<StarWarsPerson> optionalPerson = starWarsService.getStarWarsPerson(id);
		InsertOrUpdateRepository(ServiceUsed.GET);
		if (optionalPerson.isEmpty()) {
            LOGGER.debug("No such person is found: id: {}", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found : " + id);
		} else {
			return optionalPerson.get();
		}
	}
	private void InsertOrUpdateRepository(ServiceUsed service) {
		
		Optional<Stats> statsDBRecord = repository.findById(service.getId());
        Stats stats;
        if(statsDBRecord.isEmpty()) {
            stats = new Stats();
			stats.setId(service.getId());
			stats.setVisits(1);
        }else {
            stats = statsDBRecord.get();
			stats.setVisits(stats.getVisits()+1);
        }
        repository.save(stats);
    }
}

package nl.qnh.qforce.application;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.domain.StarWarsPerson;
import nl.qnh.qforce.service.PersonService;
import nl.qnh.qforce.service.PersonServiceImpl;

@RestController
public class StarWarsController {

	private static Logger LOGGER = LoggerFactory.getLogger(StarWarsController.class);
	private PersonService service = new PersonServiceImpl();


	@GetMapping("/persons/{id}")
	String getStarWarsPersonById(@PathVariable long id) {
		Optional<Person> optionalPerson = service.get(id);
		if (optionalPerson.isEmpty()) {
			LOGGER.info("No such person is found: id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found : " + id);
		} else {
			StarWarsPerson starWarsPerson = (StarWarsPerson) optionalPerson.get();
			String starWarsPersonJSON = "";
			try {
				starWarsPersonJSON = starWarsPerson.toJSON();
				LOGGER.debug("Generating JSON" + starWarsPersonJSON);
			} catch (JsonProcessingException e) {
				LOGGER.error("error writing JSON of" + starWarsPerson, e);
			}
			return starWarsPersonJSON;
		}
	}

	@GetMapping("/persons")
	Optional<Person> getStarWarsPersons() {
		LOGGER.debug("Getting all persons");
		return Optional.empty();
	}

	public PersonService getService() {
		return service;
	}

	public void setService(PersonService service) {
		this.service = service;
	}
}

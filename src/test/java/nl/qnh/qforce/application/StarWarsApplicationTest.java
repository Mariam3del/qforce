package nl.qnh.qforce.application;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import nl.qnh.qforce.database.repository.StatsRepository;
import nl.qnh.qforce.domain.ServiceUsed;

/**
 * this is an integration test to test the application from start to finish
 * @author Mariam Adel
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class StarWarsApplicationTest {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private StatsRepository repository;
	@Test
	void contextLoads() {
	}
	@Test
	void testGetLuke() throws Exception {
		 mvc.perform(get("/persons/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.name").value("Luke Skywalker"));
		 assertTrue(repository.existsById(ServiceUsed.GET.getId()));
	}
	@Test
	void testGetSkywalkers() throws Exception {
		 mvc.perform(get("/persons/?q=skywalker")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$..name").exists());
		 assertTrue(repository.existsById(ServiceUsed.SEARCH.getId()));
	}

}

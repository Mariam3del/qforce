package nl.qnh.qforce.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true,value={ "films" },allowSetters = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class StarWarsPerson {

	private String name;
	//these are strings coz it might return the text "uknown"
	private String height;
	private String mass;
	private String birthYear;
	private StarWarsGender gender;
	private List<StarWarsMovies> movies;
	
	private List<String> films;

	@JsonProperty("birth_year")
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	@JsonProperty("weight")
	public String getMass() {
		return mass;
	}

	@JsonProperty("mass")
	public void setMass(String mass) {
		this.mass = mass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	@JsonProperty("birth_year")
	public String getBirthYear() {
		return birthYear;
	}

	public StarWarsGender getGender() {
		return gender;
	}

	public String getHeight() {
		return height;
	}

	public void setGender(StarWarsGender gender) {
		this.gender = gender;
	}

	public List<String> getFilms() {
		return films;
	}

	@JsonProperty("films")
	public void setFilms(List<String> films) {
		this.films = films;
	}

	public List<StarWarsMovies> getMovies() {
		return movies;
	}

	public void setMovies(List<StarWarsMovies> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "StarWarsPerson [name=" + name + ", height=" + height + ", mass=" + mass + ", birthYear=" + birthYear
				+ ", gender=" + gender + "]";
	}

}

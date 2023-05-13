package nl.qnh.qforce.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class StarWarsPerson implements Person {

	private String name;
	private Integer height;
	private String mass;
	private String hairColor;
	private String skinColor;
	private String eyeColor;
	@SuppressWarnings("unused")
	private String birthYear;
	private StarWarsGender gender;
	private String homeworld;
	private List<String> species;
	private List<String> vehicles;
	private List<String> starships;

	public List<String> getSpecies() {
		return species;
	}

	public void setSpecies(List<String> species) {
		this.species = species;
	}

	public List<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}

	public List<String> getStarships() {
		return starships;
	}

	public void setStarships(List<String> starships) {
		this.starships = starships;
	}

	@JsonProperty("skin_color")
	public String getSkinColor() {
		return skinColor;
	}

	@JsonProperty("skin_color")
	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	@JsonProperty("eye_color")
	public String getEyeColor() {
		return eyeColor;
	}

	@JsonProperty("eye_color")
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	@JsonProperty("birth_year")
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	@JsonProperty("hair_color")
	public String getHairColor() {
		return hairColor;
	}

	@JsonProperty("hair_color")
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	@JsonProperty("birth_year")
	public String getBirthYear() {
		return null;
	}

	@Override
	public Gender getGender() {
		return null;
	}

	@Override
	public Integer getHeight() {
		return height;
	}

	public void setGender(StarWarsGender gender) {
		this.gender = gender;
	}

	@Override
	public Integer getWeight() {
		return null;
	}

	@Override
	@JsonProperty("film")
	public List<Movie> getMovies() {
		return null;
	}

	public String getHomeworld() {
		return homeworld;
	}

	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}

	@Override
	public String toString() {
		return "StarWarsPerson [name=" + name + ", height=" + height + ", mass=" + mass + ", hairColor=" + hairColor
				+ ", skinColor=" + skinColor + ", eyeColor=" + eyeColor + ", birthYear=" + birthYear + ", gender="
				+ gender + ", homeworld=" + homeworld + ", species=" + species + ", vehicles=" + vehicles
				+ ", starships=" + starships + "]";
	}
}

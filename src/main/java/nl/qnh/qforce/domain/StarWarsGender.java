package nl.qnh.qforce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Why did i create a new enum you say??? Coz I am not allowed to edit the
 * orginial code. The API would return 5 types not 4 Also by defining a String
 * with the name , you are letting jackson handle the marshalling and
 * unmarshaling
 * 
 * @author Mariam Adel
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StarWarsGender {
	// according to SWAPI docs there is only 4 types but found out by chance that
	// there is a 5th none value. mistake?
	MALE("male"), FEMALE("female"), UNKNOWN("unknown"), NOT_APPLICABLE("n/a"), NONE("none"),
	HERMAPHRODITE("hermaphrodite");

	private String gender;

	@JsonValue
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private StarWarsGender(String gender) {
		this.gender = gender;
	}
}

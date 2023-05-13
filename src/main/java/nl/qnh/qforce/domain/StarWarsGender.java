package nl.qnh.qforce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Why did i create a new enum you say??? Coz I am not allowed to edit the
 * orginial code. and it would be quite stupid to build a
 * marshaling/unmarshaling mapper when jackson provide the cabability just use
 * annotations
 * 
 * @author Mariam Adel
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StarWarsGender {
	// according to SWAPI docs there is only 4 types but found out by chance that
	// there is a 5th none value. mistake?
	MALE("male"), 
	FEMALE("female"), 
	UNKNOWN("unknown"), 
	NOT_APPLICABLE("n/a"), 
	NONE("none");

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

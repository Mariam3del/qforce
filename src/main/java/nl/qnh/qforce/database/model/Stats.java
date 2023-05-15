package nl.qnh.qforce.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This is the entity that will be created in the database during startup
 * @author Mariam Adel
 *
 */
@Entity
@Table
public class Stats {
 
	@Column
	@Id
	private int id;
	@Column 
	private int visits;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
}

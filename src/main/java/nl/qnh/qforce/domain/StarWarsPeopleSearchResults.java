package nl.qnh.qforce.domain;
/**
 * just a class to represent the JSON search result.
 * 
 * @author Mariam Adel
 *
 */

import java.util.List;

public class StarWarsPeopleSearchResults {
	private int count;
	private String next;
	private String previous; 
	private List<StarWarsPerson> results;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public List<StarWarsPerson> getResults() {
		return results;
	}
	public void setResults(List<StarWarsPerson> results) {
		this.results = results;
	}

}

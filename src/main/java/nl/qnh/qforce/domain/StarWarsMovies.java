package nl.qnh.qforce.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class StarWarsMovies {

	private String title;
	private int episodeId;
	private String director;
	private Date releaseDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("episode_id")
	public int getEpisodeId() {
		return episodeId;
	}

	@JsonProperty("episode_id")
	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@JsonProperty("release_date")
	public Date getReleaseDate() {
		return releaseDate;
	}

	@JsonProperty("release_date")
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}

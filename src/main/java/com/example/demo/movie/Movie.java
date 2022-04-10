package com.example.demo.movie;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.genre.Genre;

@Entity
@Table
public class Movie {
	@Id
	private Integer id;
	private LocalDate release_date;
	private String title;
	private String overview;
	private Integer popularity;
	private Integer vote_count;
	private Integer vote_average;
	private String original_language;
	private String poster_url;
	@ManyToMany
	@JoinTable(
			  name = "movie_genre", 
			  joinColumns = @JoinColumn(name = "movie_id"), 
			  inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres;
	
	public Movie() {
		super();
	}

	public Movie(LocalDate release_date, String title, String overview, Integer popularity,
			Integer vote_count, Integer vote_average, String original_language, String poster_url) {
		super();
		this.release_date = release_date;
		this.title = title;
		this.overview = overview;
		this.popularity = popularity;
		this.vote_count = vote_count;
		this.vote_average = vote_average;
		this.original_language = original_language;
		this.poster_url = poster_url;
	}
	
	

	@Override
	public String toString() {
		return "Movie {id=" + id + ", release_date=" + release_date + ", title=" + title + ", overview=" + overview
				+ ", popularity=" + popularity + ", vote_count=" + vote_count + ", vote_average=" + vote_average
				+ ", original_language=" + original_language + ", poster_url=" + poster_url + "}";
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getRelease_date() {
		return release_date;
	}

	public String getTitle() {
		return title;
	}

	public String getOverview() {
		return overview;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public Integer getVote_count() {
		return vote_count;
	}

	public Integer getVote_average() {
		return vote_average;
	}

	public String getOriginal_language() {
		return original_language;
	}

	public String getPoster_url() {
		return poster_url;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRelease_date(LocalDate release_date) {
		this.release_date = release_date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public void setVote_count(Integer vote_count) {
		this.vote_count = vote_count;
	}

	public void setVote_average(Integer vote_average) {
		this.vote_average = vote_average;
	}

	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}

	public void setPoster_url(String poster_url) {
		this.poster_url = poster_url;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
	
	
	
}

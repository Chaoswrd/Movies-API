package com.example.demo.genre;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Genre {
	@Id
	private Integer id;
	private String genre;
	
	public Genre() {
		super();
	}

	public Genre(String genre) {
		super();
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Genre {id=" + id + ", genre=" + genre + "}";
	}

	public Integer getId() {
		return id;
	}

	public String getGenre() {
		return genre;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
}

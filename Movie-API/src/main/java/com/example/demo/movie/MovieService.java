package com.example.demo.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
	private final MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}
	
	private Pageable formatPageable(int page, int elements, String sortName, String orderBy) {
		if(orderBy != "asc" && orderBy != "desc") 
			new IllegalStateException(String.format("Incorrect orderby, supply either asc or desc, you supplied %s", orderBy));
		Sort sort = (orderBy == "desc") ? Sort.by(sortName).descending() : Sort.by(sortName).ascending();
		return PageRequest.of(page, elements, sort);
	}
	
	private List<Movie> getMovies(Pageable pageable) {
		return this.movieRepository.findAll(pageable).getContent();
	}
	
	public List<Movie> getMoviesById(List<Integer> ids, int page, int elements, String sortName, String orderBy) {
		Pageable pageable = formatPageable(page, elements, sortName, orderBy);
		if (ids == null || ids.isEmpty()) {
			return getMovies(pageable);
		} else {
			return this.movieRepository.findByIdIn(ids, pageable).getContent();
		}	
	}
	
	public Movie getMovieById(int id) {
		Optional<Movie> movieOptional = this.movieRepository.findById((long) id);
		if(!movieOptional.isPresent()) new IllegalStateException(String.format("There is no movie with supplied id: %s", id));
		return movieOptional.get();
	}
	
}

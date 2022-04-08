package com.example.demo.movie;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/movies")
public class MovieController {
	private final MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@GetMapping
	public List<Movie> get(@RequestParam(required = false) Set<Integer> ids, @RequestParam(required = false, defaultValue="0") int page, @RequestParam(required = false, defaultValue="5") int elem, @RequestParam(required = false, defaultValue="id") String sortName, @RequestParam(required = false, defaultValue="asc") String orderBy) {
		return this.movieService.getMoviesById(ids, page, elem, sortName, orderBy);
	} 
	
	@GetMapping("/{id}")
	public Movie getIds(@PathVariable Integer id) {
		return this.movieService.getMovieById(id);
	}
}

package com.example.demo.genre;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
	private final GenreRepository repository;

	@Autowired
	public GenreService(GenreRepository repository) {
		super();
		this.repository = repository;
	}
	
	private Pageable formatPageable(int page, int elements, String sortName, String orderBy) {
		if(orderBy != "asc" && orderBy != "desc") 
			new IllegalStateException(String.format("Incorrect orderby, supply either asc or desc, you supplied %s", orderBy));
		Sort sort = (orderBy == "desc") ? Sort.by(sortName).descending() : Sort.by(sortName).ascending();
		return PageRequest.of(page, elements, sort);
	}
	
	private List<Genre> getGenres(Pageable pageable) {
		return this.repository.findAll(pageable).getContent();
	}
	
	public List<Genre> getGenresById(List<Integer> ids, int page, int elements, String sortName, String orderBy) {
		Pageable pageable = formatPageable(page, elements, sortName, orderBy);
		if (ids == null || ids.isEmpty()) {
			return getGenres(pageable);
		} else {
			return this.repository.findByIdIn(ids, pageable).getContent();
		}	
	}
	
	public Genre getMovieById(int id) {
		Optional<Genre> movieOptional = this.repository.findById((long) id);
		if(!movieOptional.isPresent()) new IllegalStateException(String.format("There is no movie with supplied id: %s", id));
		return movieOptional.get();
	}
	
}

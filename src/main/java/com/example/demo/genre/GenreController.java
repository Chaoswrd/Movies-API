package com.example.demo.genre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/genres")
public class GenreController {
	private final GenreService service;

	@Autowired
	public GenreController(GenreService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<Genre> get(
    @RequestParam(required = false) List<Integer> id,
    @RequestParam(required = false, defaultValue="0") int page,
    @RequestParam(required = false, defaultValue="5") int elem,
    @RequestParam(required = false, defaultValue="id") String sortName,
    @RequestParam(required = false, defaultValue="asc") String orderBy
  ) {
		return this.service.getGenresById(id, page, elem, sortName, orderBy);
	} 
	
}

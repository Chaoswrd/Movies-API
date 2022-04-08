package com.example.demo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.movie.Movie;
import com.example.demo.movie.MovieController;
import com.example.demo.movie.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void defaultResponse() throws Exception {
		List<Movie> mockMovieList = createMockList(range(0,4));
		when(service.getMoviesById(null, 0, 5, "id", "asc")).thenReturn(mockMovieList);
		
		final String expectedResponseContent = objectMapper.writeValueAsString(mockMovieList);
		this.mockMvc.perform(get("/api/v1/movies"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().json(expectedResponseContent));
		verify(service).getMoviesById(null, 0, 5, "id", "asc");
	}
	
	@Test
	public void checkAsc() throws Exception {
		List<Movie> mockMovieList = createMockList(range(0,4));
		when(service.getMoviesById(null, 0, 5, "id", "asc")).thenReturn(mockMovieList);
		
		final String expectedResponseContent = objectMapper.writeValueAsString(mockMovieList);
		this.mockMvc.perform(get("/api/v1/movies"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().json(expectedResponseContent));
		verify(service).getMoviesById(null, 0, 5, "id", "asc");
	}
	
	@Test
	public void checkDesc() throws Exception {
		List<Movie> mockMovieList = createMockList(reverse(range(0,4)));
		when(service.getMoviesById(null, 0, 5, "id", "desc")).thenReturn(mockMovieList);
		
		final String expectedResponseContent = objectMapper.writeValueAsString(mockMovieList);
		this.mockMvc.perform(get("/api/v1/movies?orderBy=desc"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().json(expectedResponseContent));
		verify(service).getMoviesById(null, 0, 5, "id", "desc");
	}
	
	@Test
	public void checkRandomIds() throws Exception {
		List<Integer> ids = List.of(1,3,5,7);
		List<Movie> mockMovieList = createMockList(ids);
		when(service.getMoviesById(Set.copyOf(ids), 0, 5, "id", "asc")).thenReturn(mockMovieList);
		
		final String expectedResponseContent = objectMapper.writeValueAsString(mockMovieList);
		this.mockMvc.perform(get("/api/v1/movies?ids=1,3,5,7"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().json(expectedResponseContent));
		verify(service).getMoviesById(Set.copyOf(ids), 0, 5, "id", "asc");
	}
	// Helper Functions
	private List<Integer> range(int start, int end) {
		return IntStream.rangeClosed(start, end)
			    .boxed().collect(Collectors.toList());
	}
	
	private <T> List<T> reverse (List<T> list) {
		Collections.reverse(list);
		return list;
	}

	private List<Movie> createMockList(List<Integer> ids) {
		ArrayList<Movie> mockMovieList = new ArrayList<Movie>();
		for(Integer id:ids) {
			Movie mockMovie = new Movie(LocalDate.of(2021, 12, 15),	"Spider-Man: No Way Home",	"Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",	5084,	8940,	8,	"en",	"https://image.tmdb.org/t/p/original/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg");
			mockMovie.setId(id);
			mockMovieList.add(mockMovie);
		}
		return mockMovieList;
	}
	

}

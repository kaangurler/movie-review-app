package io.github.kaangurler.movie_service.controller;

import io.github.kaangurler.movie_service.dto.MovieRequest;
import io.github.kaangurler.movie_service.dto.MovieResponse;
import io.github.kaangurler.movie_service.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/movie/")
@RequiredArgsConstructor
public class MovieController {

	private final MovieService movieService;

	@PostMapping()
	public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest movieRequest) {
		return ResponseEntity.ok(movieService.create(movieRequest));
	}

	@GetMapping("{id}")
	public ResponseEntity<MovieResponse> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(movieService.getById(id));
	}

	@GetMapping()
	public ResponseEntity<List<MovieResponse>> getAll() {
		return ResponseEntity.ok(movieService.getAll());
	}

	@PutMapping("{id}")
	public ResponseEntity<MovieResponse> updateById(@PathVariable UUID id,
			@Valid @RequestBody MovieRequest movieRequest) {
		return ResponseEntity.ok(movieService.updateById(id, movieRequest));
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable UUID id) {
		movieService.deleteById(id);
	}
}

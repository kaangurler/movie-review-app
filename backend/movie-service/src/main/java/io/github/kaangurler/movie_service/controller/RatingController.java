package io.github.kaangurler.movie_service.controller;

import io.github.kaangurler.movie_service.dto.RatingRequest;
import io.github.kaangurler.movie_service.dto.RatingResponse;
import io.github.kaangurler.movie_service.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/v1/rating/")
@RequiredArgsConstructor
public class RatingController {

	private final RatingService ratingService;

	@PostMapping("{movieId}")
	public ResponseEntity<RatingResponse> create(@PathVariable UUID movieId,
			@Valid @RequestBody RatingRequest ratingRequest) {

		return ResponseEntity.ok(ratingService.create(movieId, ratingRequest));
	}

	@GetMapping("movie/{movieId}")
	public ResponseEntity<List<RatingResponse>> getAllByMovieId(@PathVariable UUID movieId) {

		return ResponseEntity.ok(ratingService.getAllByMovieId(movieId));
	}

	@GetMapping("user/{userId}")
	public ResponseEntity<List<RatingResponse>> getAllByUserId(@PathVariable UUID userId) {

		return ResponseEntity.ok(ratingService.getAllByUserId(userId));
	}
}

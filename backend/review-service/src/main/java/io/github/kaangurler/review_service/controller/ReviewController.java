package io.github.kaangurler.review_service.controller;

import io.github.kaangurler.review_service.dto.ReviewRequest;
import io.github.kaangurler.review_service.dto.ReviewResponse;
import io.github.kaangurler.review_service.service.ReviewService;
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
@RequestMapping("api/v1/review/")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<ReviewResponse> create(@Valid @RequestBody ReviewRequest reviewRequest) {

		return ResponseEntity.ok(reviewService.create(reviewRequest));
	}

	@GetMapping("/review/{id}")
	public ResponseEntity<ReviewResponse> getById(@PathVariable UUID id) {

		return ResponseEntity.ok(reviewService.getById(id));
	}

	@GetMapping("/movie/{movieId}")
	public ResponseEntity<List<ReviewResponse>> getAllByMovieId(UUID movieId) {

		return ResponseEntity.ok(reviewService.getAllByMovieId(movieId));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<ReviewResponse>> getAllByUserId(UUID userId) {

		return ResponseEntity.ok(reviewService.getAllByUserId(userId));
	}

	@PutMapping("{id}")
	public ResponseEntity<ReviewResponse> update(@PathVariable UUID id, @Valid @RequestBody ReviewRequest reviewRequest) {

		return ResponseEntity.ok(reviewService.update(id, reviewRequest));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable UUID id) {

		reviewService.delete(id);
	}
}

package io.github.kaangurler.review_service.service.impl;

import io.github.kaangurler.review_service.dto.ReviewRequest;
import io.github.kaangurler.review_service.dto.ReviewResponse;
import io.github.kaangurler.review_service.repository.ReviewRepository;
import io.github.kaangurler.review_service.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;

	@Override
	public ReviewResponse create(ReviewRequest reviewRequest) {
		return null;
	}

	@Override
	public ReviewResponse getById(UUID id) {
		return null;
	}

	@Override
	public List<ReviewResponse> getAllByMovieId(UUID movieId) {
		return List.of();
	}

	@Override
	public List<ReviewResponse> getAllByUserId(UUID userId) {
		return List.of();
	}

	@Override
	public ReviewResponse update(UUID id, ReviewRequest reviewRequest) {
		return null;
	}

	@Override
	public void delete(UUID id) {

	}
}

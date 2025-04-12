package io.github.kaangurler.review_service.service;

import io.github.kaangurler.review_service.dto.ReviewRequest;
import io.github.kaangurler.review_service.dto.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

	ReviewResponse create(ReviewRequest reviewRequest);

	ReviewResponse getById(UUID id);

	List<ReviewResponse> getAllByMovieId(UUID movieId);

	List<ReviewResponse> getAllByUserId(UUID userId);

	ReviewResponse update(UUID id, ReviewRequest reviewRequest);

	void delete(UUID id);
}

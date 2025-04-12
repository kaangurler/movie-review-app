package io.github.kaangurler.review_service.service.impl;

import io.github.kaangurler.review_service.dto.ReviewRequest;
import io.github.kaangurler.review_service.dto.ReviewResponse;
import io.github.kaangurler.review_service.entity.Review;
import io.github.kaangurler.review_service.exception.custom.ReviewNotFoundException;
import io.github.kaangurler.review_service.mapper.ReviewMapper;
import io.github.kaangurler.review_service.repository.ReviewRepository;
import io.github.kaangurler.review_service.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;

	@Override
	public ReviewResponse create(ReviewRequest reviewRequest) {

		Review review = ReviewMapper.toReview(reviewRequest);

		Review dBReview = reviewRepository.save(review);

		return ReviewMapper.toResponse(dBReview);
	}

	@Override
	public ReviewResponse getById(UUID id) {

		Review review = reviewRepository.findById(id)
				.orElseThrow(() -> new ReviewNotFoundException("Review ID = " + id + " has not found"));

		return ReviewMapper.toResponse(review);
	}

	@Override
	public List<ReviewResponse> getAllByMovieId(UUID movieId) {

		List<Review> reviews = reviewRepository.findByMovieId(movieId);

		return reviews.parallelStream().map(ReviewMapper::toResponse).toList();
	}

	@Override
	public List<ReviewResponse> getAllByUserId(UUID userId) {

		List<Review> reviews = reviewRepository.findByUserId(userId);

		return reviews.parallelStream().map(ReviewMapper::toResponse).toList();
	}

	@Override
	public ReviewResponse update(UUID id, ReviewRequest reviewRequest) {

		Review updatedReview = reviewRepository.findById(id).map(review -> {
			review.setComment(reviewRequest.getComment());
			review.setSpoiler(reviewRequest.getSpoiler());
			review.setUpdated(LocalDateTime.now());
			return review;
		}).orElseThrow(() -> new ReviewNotFoundException("Review ID = " + id + " has not found"));

		return ReviewMapper.toResponse(updatedReview);
	}

	@Override
	public void delete(UUID id) {
		// TODO : Create status enum and set it to deleted
	}
}

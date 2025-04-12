package io.github.kaangurler.review_service.mapper;

import io.github.kaangurler.review_service.dto.ReviewRequest;
import io.github.kaangurler.review_service.dto.ReviewResponse;
import io.github.kaangurler.review_service.entity.Review;

public class ReviewMapper {

	public static ReviewResponse toResponse(Review review) {

		return ReviewResponse.builder()
				.id(review.getId())
				.userId(review.getUserId())
				.movieId(review.getMovieId())
				.comment(review.getComment())
				.spoiler(review.getSpoiler())
				.created(review.getCreated())
				.updated(review.getUpdated())
				.build();
	}

	public static Review toReview(ReviewRequest reviewRequest) {

		return Review.builder()
				.userId(reviewRequest.getUserId())
				.movieId(reviewRequest.getMovieId())
				.comment(reviewRequest.getComment())
				.spoiler(reviewRequest.getSpoiler())
				.build();
	}
}

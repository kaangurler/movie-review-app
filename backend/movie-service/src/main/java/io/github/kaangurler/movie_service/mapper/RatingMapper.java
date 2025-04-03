package io.github.kaangurler.movie_service.mapper;

import io.github.kaangurler.movie_service.dto.RatingRequest;
import io.github.kaangurler.movie_service.dto.RatingResponse;
import io.github.kaangurler.movie_service.entity.Rating;

public class RatingMapper {

	public static RatingResponse toResponse(Rating rating) {
		return RatingResponse.builder()
				.id(rating.getId())
				.userId(rating.getUserId())
				.movie(rating.getMovie())
				.value(rating.getValue())
				.build();
	}

	public static Rating toEntity(RatingRequest ratingRequest) {
		return Rating.builder()
				.userId(ratingRequest.getUserId())
				.value(ratingRequest.getValue())
				.build();
	}

}

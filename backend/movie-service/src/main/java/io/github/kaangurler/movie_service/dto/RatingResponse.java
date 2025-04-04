package io.github.kaangurler.movie_service.dto;

import io.github.kaangurler.movie_service.entity.Movie;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RatingResponse {
	private UUID id;
	private UUID userId;
	private MovieResponse movieResponse;
	private Integer value;
}

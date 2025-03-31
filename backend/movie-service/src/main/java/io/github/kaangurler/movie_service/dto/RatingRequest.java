package io.github.kaangurler.movie_service.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RatingRequest {
	@DecimalMin(value = "1", message = "User ID must be positive ")
	@NotNull(message = "User ID cannot be empty")
	private UUID userId;
	@DecimalMin(value = "1", message = "Rating cannot be less than 1")
	@DecimalMax(value = "10", message = "Rating cannot be greater than 10")
	@NotNull(message = "Rating cannot be empty")
	private Integer value;
}

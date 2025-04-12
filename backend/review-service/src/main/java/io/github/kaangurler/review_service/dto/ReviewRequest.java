package io.github.kaangurler.review_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReviewRequest {
	@NotNull(message = "User ID cannot be empty")
	private UUID userId;
	@NotNull(message = "Movie ID cannot be empty")
	private UUID movieId;
	@NotBlank(message = "Comment cannot be empty")
	private String comment;
	@NotNull(message = "Spoiler cannot be empty")
	private Boolean spoiler;
}

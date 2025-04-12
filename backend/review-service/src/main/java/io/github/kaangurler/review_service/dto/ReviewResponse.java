package io.github.kaangurler.review_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReviewResponse {
	private UUID id;
	private UUID userId;
	private UUID movieId;
	private String comment;
	private Boolean spoiler;
	private LocalDateTime created;
	private LocalDateTime updated;
}

package io.github.kaangurler.review_service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponse {
	private List<String> messages;
}

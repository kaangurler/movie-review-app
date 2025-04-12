package io.github.kaangurler.review_service.exception;

import io.github.kaangurler.review_service.dto.ErrorResponse;
import io.github.kaangurler.review_service.exception.custom.ReviewNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReviewNotFound(ReviewNotFoundException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.messages(List.of(e.getMessage()))
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}

package io.github.kaangurler.movie_service.exception;

import io.github.kaangurler.movie_service.dto.ErrorResponse;
import io.github.kaangurler.movie_service.exception.custom.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleMovieNotFound(MovieNotFoundException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.messages(List.of(e.getMessage()))
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}

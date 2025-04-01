package io.github.kaangurler.movie_service.exception.custom;

public class MovieNotFoundException extends RuntimeException {
	public MovieNotFoundException(String message) {
		super(message);
	}
}

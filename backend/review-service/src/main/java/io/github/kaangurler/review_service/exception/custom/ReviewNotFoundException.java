package io.github.kaangurler.review_service.exception.custom;

public class ReviewNotFoundException extends RuntimeException {
	public ReviewNotFoundException(String message) {
		super(message);
	}
}

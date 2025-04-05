package io.github.kaangurler.movie_service.mapper;

import io.github.kaangurler.movie_service.dto.MovieRequest;
import io.github.kaangurler.movie_service.dto.MovieResponse;
import io.github.kaangurler.movie_service.entity.Category;
import io.github.kaangurler.movie_service.entity.Movie;

import java.time.LocalDateTime;

public class MovieMapper {

	public static MovieResponse toResponse(Movie movie) {
		return MovieResponse.builder()
				.id(movie.getId())
				.title(movie.getTitle())
				.categories(Category.getTitles(movie.getCategories()))
				.casts(movie.getCasts())
				.releaseYear(movie.getReleaseYear())
				.duration(movie.getDuration())
				.storyline(movie.getStoryline())
				.averageRating(movie.getAverageRating())
				.image(movie.getImage())
				.created(movie.getCreated())
				.updated(movie.getUpdated())
				.build();
	}

	public static Movie toEntity(MovieRequest movieRequest) {
		return Movie.builder()
				.title(movieRequest.getTitle())
				.categories(movieRequest.getCategories())
				.releaseYear(movieRequest.getReleaseYear())
				.duration(movieRequest.getDuration())
				.storyline(movieRequest.getStoryline())
				.build();
	}
}

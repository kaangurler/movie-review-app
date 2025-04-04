package io.github.kaangurler.movie_service.service;

import io.github.kaangurler.movie_service.dto.MovieRequest;
import io.github.kaangurler.movie_service.dto.MovieResponse;
import io.github.kaangurler.movie_service.dto.RatingRequest;
import io.github.kaangurler.movie_service.dto.RatingResponse;
import io.github.kaangurler.movie_service.entity.Category;
import io.github.kaangurler.movie_service.entity.Movie;
import io.github.kaangurler.movie_service.entity.Rating;
import io.github.kaangurler.movie_service.exception.custom.MovieNotFoundException;
import io.github.kaangurler.movie_service.mapper.MovieMapper;
import io.github.kaangurler.movie_service.mapper.RatingMapper;
import io.github.kaangurler.movie_service.repository.MovieRepository;
import io.github.kaangurler.movie_service.repository.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

	private final MovieRepository movieRepository;
	private final RatingRepository ratingRepository;

	public MovieResponse create(MovieRequest movieRequest) {

		movieRequest.setCategories(movieRequest.getCategories()
				.stream()
				.sorted(Comparator.comparing(Category::getTitle))
				.toList());

		Movie movie = MovieMapper.toEntity(movieRequest);

		Movie dbMovie = movieRepository.save(movie);

		return MovieMapper.toResponse(dbMovie);
	}

	public MovieResponse getById(UUID id) {

		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("Movie ID = " + id + " has not found"));

		return MovieMapper.toResponse(movie);
	}

	public List<MovieResponse> getAll() {

		List<Movie> movies = movieRepository.findAll();

		return movies.parallelStream().map(MovieMapper::toResponse).toList();
	}

	public RatingResponse rateMovie(UUID movieId, RatingRequest ratingRequest) {

		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException("Movie ID = " + movieId + " has not found"));

		Rating rating = RatingMapper.toEntity(ratingRequest);
		rating.setMovie(movie);

		List<Rating> ratings = ratingRepository.findAllByMovieId(movieId);

		List<Rating> previousRatings = ratings.stream()
				.filter(r -> r.getUserId().equals(ratingRequest.getUserId()))
				.toList();

		Rating dbRating;

		if (previousRatings.isEmpty()) {

			dbRating = ratingRepository.save(rating);
			ratings.add(rating);
		} else {

			previousRatings.getFirst().setValue(rating.getValue());
			dbRating = ratingRepository.save(previousRatings.getFirst());
			ratings.get(ratings.indexOf(previousRatings.getFirst())).setValue(rating.getValue());
		}

		movie.setAverageRating(calculateAverageRating(ratings));

		movieRepository.save(movie);

		return RatingMapper.toResponse(dbRating);
	}

	public List<RatingResponse> getAllRatingsByMovieId(UUID movieId) {

		List<Rating> ratings = ratingRepository.findAllByMovieId(movieId);

		return ratings.parallelStream().map(RatingMapper::toResponse).toList();
	}

	public List<RatingResponse> getAllRatingsByUserId(UUID userId) {

		List<Rating> ratings = ratingRepository.findAllByUserId(userId);

		return ratings.parallelStream().map(RatingMapper::toResponse).toList();
	}

	public MovieResponse updateById(UUID id, MovieRequest movieRequest) {

		Movie updatedMovie = movieRepository.findById(id).map(movie -> {
			movie.setTitle(movieRequest.getTitle());
			movie.setCategories(movieRequest.getCategories());
			movie.setReleaseYear(movieRequest.getReleaseYear());
			movie.setDuration(movieRequest.getDuration());
			movie.setStoryline(movieRequest.getStoryline());
			return movie;
		}).orElseThrow(() -> new MovieNotFoundException("Movie ID = " + id + " has not found"));

		return MovieMapper.toResponse(updatedMovie);
	}

	public void deleteById(UUID id) {

		movieRepository.deleteById(id);
	}

	private double calculateAverageRating(List<Rating> ratings) {

		double averageRating = ratings.parallelStream()
				.map(Rating::getValue)
				.collect(Collectors.averagingDouble(Integer::doubleValue));

		return averageRating;
	}
}

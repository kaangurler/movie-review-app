package io.github.kaangurler.movie_service.service;

import io.github.kaangurler.movie_service.dto.MovieResponse;
import io.github.kaangurler.movie_service.dto.RatingRequest;
import io.github.kaangurler.movie_service.dto.RatingResponse;
import io.github.kaangurler.movie_service.entity.Movie;
import io.github.kaangurler.movie_service.entity.Rating;
import io.github.kaangurler.movie_service.mapper.RatingMapper;
import io.github.kaangurler.movie_service.repository.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RatingService {

	private final MovieService movieService;
	private final RatingRepository ratingRepository;

	public RatingResponse create(UUID movieId, RatingRequest ratingRequest) {

		MovieResponse movieResponse = movieService.getById(movieId);

		Rating rating = RatingMapper.toEntity(ratingRequest);

		rating.setMovie(Movie.builder().id(movieResponse.getId()).build());

		Rating dbRating = ratingRepository.save(rating);

		return RatingMapper.toResponse(dbRating);
	}

	public List<RatingResponse> getAllByUserId(UUID userId) {

		List<Rating> ratings = ratingRepository.findAllByUserId(userId);

		return ratings.stream().map(RatingMapper::toResponse).toList();
	}

	public List<RatingResponse> getAllByMovieId(UUID movieId) {

		List<Rating> ratings = ratingRepository.findAllByMovieId(movieId);

		return ratings.stream().map(RatingMapper::toResponse).toList();
	}
}

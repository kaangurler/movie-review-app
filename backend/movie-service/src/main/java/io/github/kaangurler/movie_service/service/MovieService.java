package io.github.kaangurler.movie_service.service;

import io.github.kaangurler.movie_service.dto.MovieRequest;
import io.github.kaangurler.movie_service.dto.MovieResponse;
import io.github.kaangurler.movie_service.dto.RatingRequest;
import io.github.kaangurler.movie_service.dto.RatingResponse;
import io.github.kaangurler.movie_service.entity.Category;
import io.github.kaangurler.movie_service.entity.Movie;
import io.github.kaangurler.movie_service.entity.Rating;
import io.github.kaangurler.movie_service.entity.SortDirection;
import io.github.kaangurler.movie_service.entity.SortType;
import io.github.kaangurler.movie_service.exception.custom.MovieNotFoundException;
import io.github.kaangurler.movie_service.mapper.MovieMapper;
import io.github.kaangurler.movie_service.mapper.RatingMapper;
import io.github.kaangurler.movie_service.repository.MovieRepository;
import io.github.kaangurler.movie_service.repository.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class MovieService {

	private final MovieRepository movieRepository;
	private final RatingRepository ratingRepository;

	public MovieResponse create(MovieRequest movieRequest) {

		movieRequest.setCategories(movieRequest.getCategories()
				.stream()
				.sorted(Comparator.comparing(Category::getTitle))
				.toList());

		Movie movie = MovieMapper.toEntity(movieRequest);
		movie.setCreated(LocalDateTime.now());

		Movie dbMovie = movieRepository.save(movie);

		return MovieMapper.toResponse(dbMovie);
	}

	public MovieResponse getById(UUID id) {

		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("Movie ID = " + id + " has not found"));

		return MovieMapper.toResponse(movie);
	}

	public List<MovieResponse> getByIds(List<UUID> ids, String sortType, String direction) {

		List<Movie> movies = movieRepository.findByIdIn(ids, getSort(sortType, direction));

		return movies.parallelStream().map(MovieMapper::toResponse).toList();
	}

	public Page<MovieResponse> getAll(int page, int size, String sortType, String direction, String categoryName) {

		List<Category> categories = categoryName != null ?
				List.of(Category.getByTitle(categoryName)) :
				Category.getAllCategories();

		Page<Movie> movies = movieRepository.findByCategoriesIn(categories,
				PageRequest.of(--page, size, getSort(sortType, direction)));

		return movies.map(MovieMapper::toResponse);
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

	private Sort getSort(String sortType, String direction) {

		SortDirection sortDirection = SortDirection.getByName(direction);

		String sort = SortType.getByName(sortType).getNameLowerCase();

		return sortDirection != SortDirection.DESC ? Sort.by(sort).descending(): Sort.by(sort).ascending();
	}
}

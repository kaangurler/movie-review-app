package io.github.kaangurler.movie_service.service;

import io.github.kaangurler.movie_service.dto.MovieRequest;
import io.github.kaangurler.movie_service.dto.MovieResponse;
import io.github.kaangurler.movie_service.entity.Movie;
import io.github.kaangurler.movie_service.exception.custom.MovieNotFoundException;
import io.github.kaangurler.movie_service.mapper.MovieMapper;
import io.github.kaangurler.movie_service.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

	private final MovieRepository movieRepository;

	public MovieResponse create(MovieRequest movieRequest) {

		Movie movie = MovieMapper.toEntity(movieRequest);

		Movie dbMovie = movieRepository.save(movie);

		return MovieMapper.toResponse(dbMovie);
	}

	public MovieResponse getById(UUID id) {

		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new MovieNotFoundException("Movie ID = " + id + " {} is not found"));

		return MovieMapper.toResponse(movie);
	}

	public List<MovieResponse> getAll() {

		List<Movie> movies = movieRepository.findAll();

		return movies.stream().map(MovieMapper::toResponse).toList();
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
}

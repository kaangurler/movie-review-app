package io.github.kaangurler.movie_service.repository;

import io.github.kaangurler.movie_service.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

}

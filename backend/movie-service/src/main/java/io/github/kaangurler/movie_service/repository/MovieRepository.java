package io.github.kaangurler.movie_service.repository;

import io.github.kaangurler.movie_service.entity.Category;
import io.github.kaangurler.movie_service.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

	Page<Movie> findByCategoriesIn(List<Category> categories, Pageable pageable);
}

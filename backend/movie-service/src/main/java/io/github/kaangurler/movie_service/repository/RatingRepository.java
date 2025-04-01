package io.github.kaangurler.movie_service.repository;

import io.github.kaangurler.movie_service.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
